package com.inceptai.neo.es.data;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.CLASS;
import static java.lang.annotation.RetentionPolicy.SOURCE;
/**
 * Denotes that the annotated element of integer type, represents
 * a logical type and that its value should be one of the explicitly
 * named constants. If the IntDef#flag() attribute is set to true,
 * multiple constants can be combined.
 * <p>
 * Example:
 * <pre>{@code
 *  &#64;Retention(CLASS)
 *  &#64;IntDef(&#123;NAVIGATION_MODE_STANDARD, NAVIGATION_MODE_LIST, NAVIGATION_MODE_TABS&#125;)
 *  public &#64;interface NavigationMode &#123;&#125;
 *  public static final int NAVIGATION_MODE_STANDARD = 0;
 *  public static final int NAVIGATION_MODE_LIST = 1;
 *  public static final int NAVIGATION_MODE_TABS = 2;
 *  ...
 *  public abstract void setNavigationMode(&#64;NavigationMode int mode);
 *  &#64;NavigationMode
 *  public abstract int getNavigationMode();
 * }</pre>
 * For a flag, set the flag attribute:
 * <pre>{@code
 *  &#64;IntDef(
 *      flag = true
 *      value = &#123;NAVIGATION_MODE_STANDARD, NAVIGATION_MODE_LIST, NAVIGATION_MODE_TABS&#125;)
 * }</pre>
 */
@Retention(CLASS)
@Target({ANNOTATION_TYPE})
public @interface IntDef {
  /** Defines the allowed constants for this element */
  long[] value() default {};
  /** Defines whether the constants can be used as a flag, or just as an enum (the default) */
  boolean flag() default false;
}