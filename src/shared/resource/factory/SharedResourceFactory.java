package shared.resource.factory;

import shared.resource.SharedResource;

public class SharedResourceFactory implements ResourceFactory<SharedResource>{
    @Override
    public SharedResource produce() {
        return new SharedResource();
    }
}
