package org.zs.phm3.service.rpm;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.dto.ptl.DtoIdIntName;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@FeignClient(name = "gjb-jdbc-h2")

//    @FeignClient(name="gjb-jdbc-h2", url="localhost:8081")
@RibbonClient(name = "GJB-JDBC-H2")
public interface GjbFeignProxyClient {

    @GetMapping("/api/getStatus")
    public List<String> getGjbStatus();

    //    @RequestMapping(method = RequestMethod.GET, value = "/api/getGjbResult/{elementClass}/{paramCode}", produces = "application/json")
    @PostMapping(value = "/api/getGjbResult/{elementClass}/{paramCode}", produces = "application/json")
    Double getGjbResult(@PathVariable("elementClass") Integer elementClass,
                        @PathVariable("paramCode") Integer paramCode,
                        @RequestParam Map<String, String> inputParametersValuesList);


    @GetMapping(value = "/api/getElementClasses", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<DtoIdIntName> getElementClasses();


    @PostMapping(value = "/api/testParameters/{elementClass}/{paramCode}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public String testParameters(@PathVariable("elementClass") Integer elementClass,
                                 @PathVariable("paramCode") Integer paramCode,
                                 @RequestParam Map<String, String> inputParametersValuesList);
}

