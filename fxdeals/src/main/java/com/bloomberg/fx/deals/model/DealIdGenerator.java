package com.bloomberg.fx.deals.model;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.UUID;

public class DealIdGenerator implements IdentifierGenerator {
   public static final String generatorName = "idGenerator";

   @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object object) throws HibernateException {
       return UUID.randomUUID().toString().replace("-", "");
   }
}
