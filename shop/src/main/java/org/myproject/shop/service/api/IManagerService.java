package org.myproject.shop.service.api;

import org.myproject.shop.rest.dto.ManagerDto;
import java.util.List;

public interface IManagerService {
    List<ManagerDto> getManagers();

    ManagerDto getManager(long id);

    boolean addManager(ManagerDto manager);

    boolean updateManager(long id);

    boolean deleteManager(long id);
}
