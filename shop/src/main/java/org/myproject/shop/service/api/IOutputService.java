package org.myproject.shop.service.api;


import org.myproject.shop.rest.dto.OutputDto;
import java.util.List;


public interface IOutputService {
    List<OutputDto> getOutputs();

    OutputDto getOutput(long id);

    boolean addOutput(OutputDto output);
}
