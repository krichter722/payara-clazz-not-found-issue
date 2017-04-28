/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package richtercloud.payara.clazz.not.found.issue.web;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.event.AjaxBehaviorEvent;
import javax.measure.unit.Unit;
import richtercloud.payara.clazz.not.found.issue.jar.MyEntity;

/**
 *
 * @author richter
 */
@ManagedBean
public class MyCtrl implements Serializable {
    private static final long serialVersionUID = 1L;
    private MyEntity entity = new MyEntity();
    private Converter unitConverter = new Converter() {
        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            if(value == null) {
                return null;
            }
            Unit<?> retValue = Unit.valueOf(value);
            return retValue;
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            if(value == null) {
                return null;
            }
            assert value instanceof Unit;
            Unit<?> valueCast = (Unit) value;
            String retValue = valueCast.toString();
            return retValue;
        }
    };
    private Class<?> elementClass;

    public MyCtrl() {
        try {
            this.elementClass = Class.forName("javax.measure.unit.ProductUnit$Element");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Class<?> getElementClass() {
        return elementClass;
    }

    public void setElementClass(Class<?> elementClass) {
        this.elementClass = elementClass;
    }

    public MyEntity getEntity() {
        return entity;
    }

    public void setEntity(MyEntity entity) {
        this.entity = entity;
    }

    public Converter getUnitConverter() {
        return unitConverter;
    }

    public void setUnitConverter(Converter unitConverter) {
        this.unitConverter = unitConverter;
    }

    public void checkClassUsable(AjaxBehaviorEvent event) {
        try {
            this.elementClass = Class.forName("javax.measure.unit.ProductUnit$Element");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
}
