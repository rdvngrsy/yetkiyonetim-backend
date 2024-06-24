package com.yetkiyonetim.yetkiyonetim.core.utilities.mappers.modelMapper;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {
    ModelMapper forResponse();
    ModelMapper forRequest();
}
