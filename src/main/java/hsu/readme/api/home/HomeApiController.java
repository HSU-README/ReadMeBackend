package hsu.readme.api.home;

import hsu.readme.service.AdService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeApiController {

    private final AdService adService;
}
