package shared.resource.factory;

import shared.resource.Resource;

public interface ResourceFactory<T extends Resource> {
    T produce();
}
