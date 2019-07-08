package org.myproject.shop.service.api;

import java.util.List;

import org.myproject.shop.rest.dto.InputDto;


public interface IInputService {
    List<InputDto> getInputs();

    InputDto getInput(long id);

    boolean addInput(InputDto input );

}
