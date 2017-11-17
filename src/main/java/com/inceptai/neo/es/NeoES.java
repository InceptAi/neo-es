package com.inceptai.neo.es;

import static java.util.Collections.singleton;

import com.inceptai.neo.es.OntologyUtils.AxiomAdder;
import com.inceptai.neo.es.data.BandwidthGrade;
import com.inceptai.neo.es.data.HttpGrade;
import com.inceptai.neo.es.data.InferenceBundle;
import com.inceptai.neo.es.data.PingGrade;
import com.inceptai.neo.es.data.WifiGrade;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.OWLXMLDocumentFormat;
import org.semanticweb.owlapi.io.StringDocumentSource;
import org.semanticweb.owlapi.model.AxiomType;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLObjectSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.util.OWLOntologyWalker;
import org.semanticweb.owlapi.util.OWLOntologyWalkerVisitorEx;

public class NeoES {

  private OWLOntologyManager owlOntologyManager;
  private OWLOntology ontology;

  private static Logger NL = Logger.get(NeoES.class);

  private NeoES(OWLOntologyManager owlOntologyManager, OWLOntology initOntology) {
    this.owlOntologyManager = owlOntologyManager;
    this.ontology = initOntology;
  }

  /**
   *
   * @param owlFile
   * @return Instance of the NeoES object or null on error.
   */
  public static NeoES buildNeoExpertSystem(String owlFile) {
    OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
    OWLOntology owlOntology;

    String readFile="";
    try {
      readFile = FileUtils.readFileToString(new File(owlFile), (String) null);
    } catch (IOException e) {
      // Error logger.
      NL.e("Exception opening ontology file: " + e.toString());
      return null;
    }
    try {
      owlOntology = manager.loadOntologyFromOntologyDocument(new StringDocumentSource(readFile));

    } catch (OWLOntologyCreationException e) {
      NL.e("Exception loading ontology from string document: " + e.toString());
      return null;
    }
    return new NeoES(manager, owlOntology);
  }

  /**
   * Adds the given OWL file to the basic ontology.
   *
   */
  public void addOntology() {

  }

  public void addInference(InferenceBundle bundle) {
    addWifiGrade(bundle.getWifiGrade());
    addPingGrade(bundle.getPingGrade());
    addBandwidthGrade(bundle.getBandwidthGrade());
    addHttpGrade(bundle.getHttpGrade());
  }

  /**
   * Saves the ontology in an OWL/XML format.
   *
   * @param outFile Output file name for the OWL format.
   * @return false on failure, true on success.
   */
  public boolean saveOntology(String outFile) {
    OutputStream outputStream;
    try {
      outputStream = FileUtils.openOutputStream(new File(outFile));
    } catch (IOException e) {
      NL.e("saveOntology FAILED. Unable to open file: " + outFile);
      return false;
    }
    try {
      owlOntologyManager.saveOntology(ontology, new OWLXMLDocumentFormat(), outputStream);
    } catch (OWLOntologyStorageException e) {
      NL.e("Exception trying to serialize ontology to output file.");
      return false;
    }
    return true;
  }

  public void walkOntology() {
    System.out.println(ontology.toString());
    OWLOntologyWalker walker = new OWLOntologyWalker(singleton(ontology));

    OWLOntologyWalkerVisitorEx<Object> visitor = new OWLOntologyWalkerVisitorEx<Object>(walker) {

      public Object visit(OWLObjectSomeValuesFrom ce) {
        // Print out the restriction
        System.out.println("NODE:" + ce.toString());
        // Print out the axiom where the restriction is used
        System.out.println("   has axiom:  " + getCurrentAxiom());
        // We don't need to return anything here.
        return "";
      }
    };
    // Now ask the walker to walk over the ontology structure using our
    // visitor instance.
    walker.walkStructure(visitor);
  }

  private void addWifiGrade(WifiGrade wifiGrade) {
    Optional<IRI> iri = ontology.getOntologyID().getOntologyIRI();
    if (!iri.isPresent()) {
      NL.e("No IRI found for stored ontology. Unable to add WifiGrade.");
      return;
    }
    IRI ontologyIRI = iri.get();
    OWLDataFactory factory = owlOntologyManager.getOWLDataFactory();
    OWLIndividual wifiNetwork = factory.getOWLNamedIndividual(IRI.create(ontologyIRI +
        OntologyConstants.wifiNetworkIndividual()));

    AxiomAdder axiomAdder = new AxiomAdder(factory, wifiNetwork, ontologyIRI);
    axiomAdder.addProperty(OntologyConstants.Wifi.primaryApSignalMetricProperty(), wifiGrade.getPrimaryApSignalMetric());
    axiomAdder.addProperty(OntologyConstants.Wifi.primaryApBssidProperty(), wifiGrade.getPrimaryApBSSID());
    axiomAdder.addProperty(OntologyConstants.Wifi.primaryApChannelProperty(), wifiGrade.getPrimaryApChannel());
    axiomAdder.addProperty(OntologyConstants.Wifi.primaryApSsidProperty(), wifiGrade.getPrimaryApSsid());
    axiomAdder.addProperty(OntologyConstants.Wifi.wifiConnectivityModeProperty(), wifiGrade.getWifiConnectivityMode());
    axiomAdder.addProperty(OntologyConstants.Wifi.wifiDataRateProperty(), wifiGrade.getLinkSpeed());
    axiomAdder.addProperty(OntologyConstants.Wifi.wifiErrorCodeProperty(), wifiGrade.getErrorCode());
    axiomAdder.addProperty(OntologyConstants.Wifi.wifiLinkModeProperty(), wifiGrade.getWifiLinkMode());
    axiomAdder.addProperty(OntologyConstants.Wifi.primaryLinkChannelOccupancyMetricProperty(), wifiGrade.getPrimaryLinkChannelOccupancyMetric());
    axiomAdder.addProperty(OntologyConstants.Wifi.primaryApLinkSpeedProperty(), wifiGrade.getLinkSpeed());
    axiomAdder.addProperty(OntologyConstants.Wifi.primaryApLinkSpeedMetricProperty(), wifiGrade.getPrimaryApLinkSpeedMetric());

    owlOntologyManager.addAxioms(ontology, axiomAdder.asSet().stream());
  }


  private void addHttpGrade(HttpGrade httpGrade) {
    Optional<IRI> iri = ontology.getOntologyID().getOntologyIRI();
    if (!iri.isPresent()) {
      NL.e("No IRI found for stored ontology. Unable to add WifiGrade.");
      return;
    }
    IRI ontologyIRI = iri.get();
    OWLDataFactory factory = owlOntologyManager.getOWLDataFactory();
    OWLIndividual internetConnection = factory.getOWLNamedIndividual(IRI.create(ontologyIRI +
        OntologyConstants.internetConnectionIndividual()));

    AxiomAdder axiomAdder = new AxiomAdder(factory, internetConnection, ontologyIRI);
    axiomAdder.addProperty(OntologyConstants.Http.downloadLatencyMetricProperty(), httpGrade.getHttpDownloadLatencyMetric());
    axiomAdder.addProperty(OntologyConstants.Http.httpErrorCodeProperty(), httpGrade.getErrorCode());
    axiomAdder.addProperty(OntologyConstants.Http.httpDownloadLatencyMsProperty(), httpGrade.getHttpDownloadLatencyMs());

    owlOntologyManager.addAxioms(ontology, axiomAdder.asSet().stream());
  }

  private void addPingGrade(PingGrade pingGrade) {
    Optional<IRI> iri = ontology.getOntologyID().getOntologyIRI();
    if (!iri.isPresent()) {
      NL.e("No IRI found for stored ontology. Unable to add WifiGrade.");
      return;
    }
    IRI ontologyIRI = iri.get();
    OWLDataFactory factory = owlOntologyManager.getOWLDataFactory();
    OWLIndividual internetConnection = factory.getOWLNamedIndividual(IRI.create(ontologyIRI +
        OntologyConstants.internetConnectionIndividual()));

    AxiomAdder axiomAdder = new AxiomAdder(factory, internetConnection, ontologyIRI);
    axiomAdder.addProperty(OntologyConstants.Ping.dnsLatencyMetricProperty(), pingGrade.getDnsServerLatencyMetric());
    axiomAdder.addProperty(OntologyConstants.Ping.dnsLatencyMsProperty(), pingGrade.getDnsServerLatencyMs());
    axiomAdder.addProperty(OntologyConstants.Ping.routerLatencyMetricProperty(), pingGrade.getRouterLatencyMetric());
    axiomAdder.addProperty(OntologyConstants.Ping.routerLatencyMsProperty(), pingGrade.getRouterLatencyMs());
    axiomAdder.addProperty(OntologyConstants.Ping.alternativeDnsLatencyMetricProperty(), pingGrade.getAlternativeDnsMetric());
    axiomAdder.addProperty(OntologyConstants.Ping.alternativeDnsLatencyMsProperty(), pingGrade.getAlternativeDnsLatencyMs());
    axiomAdder.addProperty(OntologyConstants.Ping.externalServerLatencyMetricProperty(), pingGrade.getExternalServerLatencyMetric());
    axiomAdder.addProperty(OntologyConstants.Ping.externalServerLatencyMsProperty(), pingGrade.getExternalServerLatencyMs());

    owlOntologyManager.addAxioms(ontology, axiomAdder.asSet().stream());
  }

  private void addBandwidthGrade(BandwidthGrade bandwidthGrade) {
    Optional<IRI> iri = ontology.getOntologyID().getOntologyIRI();
    if (!iri.isPresent()) {
      NL.e("No IRI found for stored ontology. Unable to add WifiGrade.");
      return;
    }
    IRI ontologyIRI = iri.get();
    OWLDataFactory factory = owlOntologyManager.getOWLDataFactory();
    OWLIndividual internetConnection = factory.getOWLNamedIndividual(IRI.create(ontologyIRI +
        OntologyConstants.internetConnectionIndividual()));

    AxiomAdder axiomAdder = new AxiomAdder(factory, internetConnection, ontologyIRI);
    axiomAdder.addProperty(OntologyConstants.Bandwidth.downloadMbpsProperty(), bandwidthGrade.getDownloadMbps());
    axiomAdder.addProperty(OntologyConstants.Bandwidth.downloadBandwidthMetricProperty(), bandwidthGrade.getDownloadBandwidthMetric());
    axiomAdder.addProperty(OntologyConstants.Bandwidth.uploadMbpsProperty(), bandwidthGrade.getUploadMbps());
    axiomAdder.addProperty(OntologyConstants.Bandwidth.uploadBandwidthMetricProperty(), bandwidthGrade.getUploadBandwidthMetric());
    axiomAdder.addProperty(OntologyConstants.Bandwidth.bandwidthErrorCodeProperty(), bandwidthGrade.getErrorCode());
    axiomAdder.addProperty(OntologyConstants.Bandwidth.bestServerLatencyMsProperty(), bandwidthGrade.getBestServerLatencyMs());

    owlOntologyManager.addAxioms(ontology, axiomAdder.asSet().stream());
  }

  public void printOntology() {
    Set<OWLClass> classes;
    Set<OWLObjectProperty> prop;
    Set<OWLDataProperty> dataProp;
    Set<OWLNamedIndividual> individuals;

    classes = ontology.getClassesInSignature();
    prop = ontology.getObjectPropertiesInSignature();
    dataProp = ontology.getDataPropertiesInSignature();
    individuals = ontology.getIndividualsInSignature();

    System.out.println("Classes");
    System.out.println("--------------------------------");
    for (OWLClass cls : classes) {
      System.out.println("+: " + cls.getIRI().getShortForm());

      System.out.println(" \tObject Property Domain");
      for (OWLObjectPropertyDomainAxiom op : ontology.getAxioms(AxiomType.OBJECT_PROPERTY_DOMAIN)) {
        if (op.getDomain().equals(cls)) {
          for(OWLObjectProperty oop : op.getObjectPropertiesInSignature()){
            System.out.println("\t\t +: " + oop.getIRI().getShortForm());
          }
          System.out.println("\t\t +: " + op.getProperty().getNamedProperty().getIRI().getShortForm());
        }
      }

      System.out.println(" \tData Property Domain");
      for (OWLDataPropertyDomainAxiom dp : ontology.getAxioms(AxiomType.DATA_PROPERTY_DOMAIN)) {
        if (dp.getDomain().equals(cls)) {
          for(OWLDataProperty odp : dp.getDataPropertiesInSignature()){
            System.out.println("\t\t +: " + odp.getIRI().getShortForm());
          }
          System.out.println("\t\t +:" + dp.getProperty());
        }
      }
    }

    System.out.println("Data properties.");
    for (OWLDataProperty owlDataProperty : dataProp) {
      System.out.println("DP: " + owlDataProperty.getIRI().getShortForm());
      System.out.println("    Full string: " + owlDataProperty.getIRI().getIRIString());
    }
  }
}
