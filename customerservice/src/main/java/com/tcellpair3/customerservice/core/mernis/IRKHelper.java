package com.tcellpair3.customerservice.core.mernis;

//------------------------------------------------------------------------
//
// Generated by www.easywsdl.com
// Version: 8.0.1.2
//
// Created by Quasar Development 
//
// This class has been generated for test purposes only and cannot be used in any commercial project.
// To use it in commercial project, you need to generate this class again with Premium account.
// Check https://EasyWsdl.com/Payment/PremiumAccountDetails to see all benefits of Premium account.
//
// Licence: 7514DA398BDE186C83FF2FCF62343D2D30B722E6C1FE5D70086D8F599FE5600238108EF4A15D8376A5246B28AB17D62381E279BBD89E4E3F3C1DD24EDBF7667B
//------------------------------------------------------------------------
import java.util.*;
import org.ksoap2.serialization.*;
import org.kxml2.kdom.Element;
import org.kxml2.kdom.Node;


public class IRKHelper
{
    public static < T, E> T getKeyByValue(java.util.Map< T, E> map, E value) {
        for (java.util.Map.Entry< T, E> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static java.lang.Object getAttribute(AttributeContainer obj,java.lang.String name,java.lang.String namespace)
    {
        for (int i=0;i < obj.getAttributeCount();i++){
            AttributeInfo info = new AttributeInfo();
            obj.getAttributeInfo(i,info);
            if(info.name.equals(name) && info.namespace.equals(namespace))
            {
                return info.getValue();
            }
       }
       return null;
    }


    public static Element convertToHeader(java.lang.Object obj,java.lang.String namespace,java.lang.String name)
    {
        org.kxml2.kdom.Element parentElement = new org.kxml2.kdom.Element().createElement(namespace,name);
		if(obj==null)
        {
            return parentElement;
        }
        if(obj instanceof  KvmSerializable)
        {
            KvmSerializable soapObject =(KvmSerializable)obj;
            for(int i = 0;i < soapObject.getPropertyCount();i++){
                PropertyInfo info = new PropertyInfo();
                soapObject.getPropertyInfo(i,new Hashtable(),info);
                java.lang.Object value=soapObject.getProperty(i);
                if(value!=null && value!=SoapPrimitive.NullSkip && value!=SoapPrimitive.NullNilElement)
                {
                    info.setValue(value);
                    Element el1= convertToHeader( info.getValue(),info.getNamespace(),info.getName());
                    parentElement.addChild(Node.ELEMENT, el1);
                }
            }
        }
        else if(obj!=null && obj!=SoapPrimitive.NullSkip && obj!=SoapPrimitive.NullNilElement)
        {
            java.lang.String value=obj.toString();
            if (obj instanceof java.util.Date)
            {
                java.util.Date date = (java.util.Date) obj;
                value = IRKExtendedSoapSerializationEnvelope.getDateTimeConverter().getStringFromDateTime(date);
            }
            parentElement.addChild(org.kxml2.kdom.Node.TEXT,value);
        }
        if(obj instanceof AttributeContainer)
        {
            AttributeContainer attrContainer= (AttributeContainer)obj;
            for(int i=0;i < attrContainer.getAttributeCount();i++)
            {
                AttributeInfo info=new AttributeInfo();
                attrContainer.getAttributeInfo(i,info);
                Object value=info.getValue();
                parentElement.setAttribute(info.namespace,info.name,value!=null?value.toString():"" );
            }
        }
        return parentElement;
    }

    public static Element findOutHeader(java.lang.String name,SoapSerializationEnvelope envelope)
    {
        if(envelope.headerIn==null)
        {
            return null;
        }
        for(int i=0;i < envelope.headerIn.length;i++) {
            Element elem=envelope.headerIn[i];
            if(elem.getName().equals(name) && (elem.getChildCount()>0||elem.getAttributeCount()>0))
                return elem;
        }
        return null;
    }

    public static java.lang.Object convertToSoapObject(Element element)
    {
		if(element.getChildCount()==0 || (element.getChildCount()==1 && !(element.getChild(0) instanceof  Element)))
		{
		    SoapPrimitive primitive = new SoapPrimitive(element.getNamespace(),element.getName(),element.getChildCount()==1? element.getText(0):null);
		    return primitive;
		}
		else
		{
            SoapObject obj = new SoapObject(element.getNamespace(),element.getName());
            for (int i=0;i < element.getChildCount();i++)
            {
                Element childElement=element.getElement(i);
                java.lang.Object childObject=convertToSoapObject(childElement);
                if(childObject instanceof SoapObject)
                {
                    SoapObject soapObj= (SoapObject) childObject;
                    obj.addProperty(soapObj.getName(),childObject);
                }
                else
                {
                    SoapPrimitive primitive= (SoapPrimitive) childObject;
                    obj.addProperty(primitive.getName(),primitive);
                }
            }
		    return obj;
		}
	}
    public static boolean isEmpty(CharSequence str) {
        return str == null || str.length() == 0;
    }

    public static ArrayList< PropertyInfo> getProperties(SoapObject soapObject,String name)
    {
        ArrayList< PropertyInfo> list = new ArrayList< PropertyInfo>();
        int size = soapObject.getPropertyCount();
        for (int i0=0;i0 < size;i0++)
        {
            PropertyInfo info=soapObject.getPropertyInfo(i0);
            if ( info.name.equals(name))
            {
                list.add(info);
            }
        }
        return list;
    }
    
    public static UUID emptyGuid()
    {
        return new UUID(0,0);
    }
}