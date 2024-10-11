package org.zs.phm3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.service.ts.TsKvCustomService;
import org.zs.phm3.service.ts.TsKvIntegrationService;
import org.zs.phm3.service.ts.TsKvService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/ts")
public class TsKvController extends BaseController {
	@Autowired
	TsKvCustomService tsKvCustomService;
	@Autowired
	TsKvIntegrationService tsKvIntegrationService;
	@Autowired
	private TsKvService tsKvService;

	@GetMapping(value = "/unitid_with_tsdata")
	public List<String> getAllUnitIdWithTsData() {
		return  tsKvService.getAllUnitIdWithTsData();
	}

	@PostMapping("import_custom_ts_from_file")
	public String importToCustomTsFromCsvFile() throws IOException, InterruptedException {
		return tsKvIntegrationService.importToCustomTsFromCsvFile();
	}

	@PostMapping("copy_custom_ts_to_ts/{customUnitId}/{unitId}")
	public String copyingCustomTsToTs(@PathVariable("customUnitId") String customUnitId, @PathVariable("unitId")String unitId) {
		return tsKvIntegrationService.copyingCustomTsToTs(customUnitId, unitId);
	}
}
