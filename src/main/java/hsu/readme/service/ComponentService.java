package hsu.readme.service;

import hsu.readme.Repository.ComponentRepository;
import hsu.readme.domain.component.Component;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ComponentService {

    private final ComponentRepository componentRepository;

    @Transactional
    public void saveComponent(Component component) { componentRepository.save(component); }

    public List<Component> findComponents() {return componentRepository.findAll(); }

    public Component findOne(Long componentId) {return componentRepository.findOne(componentId); }
}
