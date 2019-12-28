package com.zhangzhao.processor;

import com.zhangzhao.annotation.Factory;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.MirroredTypeException;
import org.apache.commons.lang3.StringUtils;

public class FactoryAnnotatedClass {

  private TypeElement annotatedClassElement;
  private String qualifiedGroupClassName;
  private String simpleFactoryGroupName;
  private String id;

  public FactoryAnnotatedClass(TypeElement classElement) throws ProcessingException {
    this.annotatedClassElement = classElement;
    Factory annotation = classElement.getAnnotation(Factory.class);
    id = annotation.id();

    if (StringUtils.isEmpty(id)) {
      throw new ProcessingException(classElement,
          "id() in @%s for class %s is null or empty! that's not allowed",
          Factory.class.getSimpleName(), classElement.getQualifiedName().toString());
    }

    //Get the full QualifiedTypeName
    try {
      Class<?> clazz = annotation.type();
      qualifiedGroupClassName = clazz.getCanonicalName();
      simpleFactoryGroupName = clazz.getSimpleName();
    } catch (MirroredTypeException mte) {
      DeclaredType classTypeMirror = (DeclaredType) mte.getTypeMirror();
      TypeElement classTypeElement = (TypeElement) classTypeMirror.asElement();
      qualifiedGroupClassName = classTypeElement.getQualifiedName().toString();
      simpleFactoryGroupName = classTypeElement.getSimpleName().toString();
    }
  }

  /**
   * The original element that was annotated with @Factory
   */
  public TypeElement getTypeElement() {
    return annotatedClassElement;
  }

  /**
   * Get the simple name of the type specified in {@link Factory#type()}
   */
  public String getQualifiedFactoryGroupName() {
    return qualifiedGroupClassName;
  }

  /**
   * Get the full qualified name of the type specified in {@link Factory#type()}
   */
  public String getSimpleFactoryGroupName() {
    return simpleFactoryGroupName;
  }

  /**
   * Get the id as specified in {@link Factory#id()}
   */
  public String getId() {
    return id;
  }
}
