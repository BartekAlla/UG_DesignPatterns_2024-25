package ug.pz;

import ug.pz.ObjectFactory;

public class PooledObjectFactory implements ObjectFactory<PooledObject> {
    @Override
    public PooledObject createObject() {
        return new PooledObject();
    }
}
