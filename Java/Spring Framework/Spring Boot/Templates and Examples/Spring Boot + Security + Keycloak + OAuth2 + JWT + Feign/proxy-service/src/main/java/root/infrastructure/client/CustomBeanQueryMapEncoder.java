/**
 * Copyright 2012-2019 The Feign Authors
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package root.infrastructure.client;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import feign.QueryMapEncoder;
import feign.codec.EncodeException;

public class CustomBeanQueryMapEncoder implements QueryMapEncoder
{
    private final Map<Class<?>, CustomBeanQueryMapEncoder.ObjectParamMetadata> classToMetadata = new HashMap<>();

    @Override
    public Map<String, Object> encode(Object object) throws EncodeException
    {
        try
        {
            CustomBeanQueryMapEncoder.ObjectParamMetadata metadata = getMetadata(object.getClass());
            Map<String, Object> propertyNameToValue = new HashMap<String, Object>();
            for (PropertyDescriptor pd : metadata.objectProperties)
            {
                Object value = pd.getReadMethod().invoke(object);
                if (value != null && value != object)
                {
                    if (value.getClass().isArray())
                    {
                        Object[] arr = (Object[]) value;
                        value = Arrays.stream(arr).map(Object::toString).collect(Collectors.joining(","));
                    }
                    propertyNameToValue.put(pd.getName(), value);
                }
            }
            return propertyNameToValue;
        }
        catch (IllegalAccessException | IntrospectionException | InvocationTargetException e)
        {
            throw new EncodeException("Failure encoding object into query map", e);
        }
    }

    private CustomBeanQueryMapEncoder.ObjectParamMetadata getMetadata(Class<?> objectType) throws IntrospectionException
    {
        CustomBeanQueryMapEncoder.ObjectParamMetadata metadata = classToMetadata.get(objectType);
        if (metadata == null)
        {
            metadata = CustomBeanQueryMapEncoder.ObjectParamMetadata.parseObjectType(objectType);
            classToMetadata.put(objectType, metadata);
        }
        return metadata;
    }

    private static class ObjectParamMetadata
    {

        private final List<PropertyDescriptor> objectProperties;

        private ObjectParamMetadata(List<PropertyDescriptor> objectProperties)
        {
            this.objectProperties = Collections.unmodifiableList(objectProperties);
        }

        private static CustomBeanQueryMapEncoder.ObjectParamMetadata parseObjectType(Class<?> type)
                throws IntrospectionException
        {
            List<PropertyDescriptor> properties = new ArrayList<PropertyDescriptor>();

            for (PropertyDescriptor pd : Introspector.getBeanInfo(type).getPropertyDescriptors())
            {
                boolean isGetterMethod = pd.getReadMethod() != null && !"class".equals(pd.getName());
                if (isGetterMethod)
                {
                    properties.add(pd);
                }
            }

            return new CustomBeanQueryMapEncoder.ObjectParamMetadata(properties);
        }
    }
}
