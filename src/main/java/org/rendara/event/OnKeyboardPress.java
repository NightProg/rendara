package org.rendara.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface OnKeyboardPress {
    KeyboardKind value();
    KeyboardModifier[] modifiers() default {};
}
