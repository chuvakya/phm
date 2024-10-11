package org.zs.phm3.controller;
/**
 * Copyright © 2019-2020 The CETC PHM Authors
 *
*/

import org.zs.phm3.models.ts.TsKvHyper;
import org.zs.phm3.util.mapping.PhmUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.service.ts.TsKvHyperService;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/telemetry/hyper")
public class TsKvHyperTelemetryController extends BaseController {

	@Autowired
	private TsKvHyperService hyperService;
	
	// Hyper data
	// limit: > strartTs and <= endTs
	// получение данных для Unit по ключу за период
	// unitId - идентификатор Unit
	// key - ключ атрибута
	// strartTs - начало периода (строго > заданного)
	// endTs - конец период (<= включая заданный)
	@GetMapping(value = "/ts/{unitId}/{key}")
	public List<TsKvHyper> getTs(@PathVariable("unitId") String unitId,
                                 @PathVariable("key") String key,
                                 @RequestParam(name = "strartTs", required = false) Long startTs,
                                 @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			return hyperService.findAllByTs(PhmUtil.toShortUUID(unitId), key, startTs, endTs);
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	// limit: >= strartTs and <= endTs
	// получение данных для Unit по ключу за период
	// unitId - идентификатор Unit
	// key - ключ атрибута
	// strartTs - начало периода (>= включая заданный)
	// endTs - конец период (<= включая заданный)
	@GetMapping(value = "/tsbetween/{unitId}/{key}")
	public List<TsKvHyper> getTsBetween(@PathVariable("unitId") String unitId,
                                        @PathVariable("key") String key,
                                        @RequestParam(name = "strartTs", required = false) Long startTs,
                                        @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			return hyperService.findAllByTsBetween(PhmUtil.toShortUUID(unitId), key, startTs, endTs);
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	// получение ВСЕХ данных для Unit
	@GetMapping(value = "/{unitId}")
	public List<TsKvHyper> getTs(@PathVariable("unitId") String unitId) throws PhmException {
		try {
			return hyperService.findAll(PhmUtil.toShortUUID(unitId)); //findAllByEntityId(unitId);
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	// получение ВСЕХ данных для Unit по ключу
	@GetMapping(value = "/{unitId}/{key}")
	public List<TsKvHyper> getTs(@PathVariable("unitId") String unitId,
                                 @PathVariable("key") String key) throws PhmException {
		try {
			return hyperService.findAllByEntityIdAndKey(PhmUtil.toShortUUID(unitId), key);
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	// получение максимального значения для Unit по ключу и типу
	@GetMapping(value = "/value/max/{unitId}/{key}")
	public Object getTsValueMax(@PathVariable("unitId") String unitId, 
			   				    @PathVariable("key") String key,
			   				    @RequestParam(name = "valueType", required = false) String valueType) throws PhmException {
		try {
			if (valueType.equals("LONG")) {
				return hyperService.maxLong(PhmUtil.toShortUUID(unitId), key);
			} else {
				return hyperService.maxDouble(PhmUtil.toShortUUID(unitId), key);
			}
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	// получение минимального значения для Unit по ключу и типу
	@GetMapping(value = "/value/min/{unitId}/{key}")
	public Object getTsValueMin(@PathVariable("unitId") String unitId, 
			   				    @PathVariable("key") String key,
			   				    @RequestParam(name = "valueType", required = false) String valueType) throws PhmException {
		try {
			if (valueType.equals("LONG")) {
				return hyperService.minLong(PhmUtil.toShortUUID(unitId), key);
			} else {
				return hyperService.minDouble(PhmUtil.toShortUUID(unitId), key);
			}
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	// получение упорядоченного списка всех атрибутов для Unit
	@GetMapping(value = "/keys/{unitId}")
	public Object getTsKeys(@PathVariable("unitId") String unitId) throws PhmException {
		try {
			return hyperService.getKeys(PhmUtil.toShortUUID(unitId));
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	// получение списка всех значений для Unit по ключу и типу
	@GetMapping(value = "/values/{unitId}/{key}")
	public List<Object> getTsValues(@PathVariable("unitId") String unitId, 
			   				  		@PathVariable("key") String key,
			   				  		@RequestParam(name = "valueType", required = true) String valueType) throws PhmException {
		try {
			if (valueType.equals("DOUBLE")) {
				return Arrays.asList(hyperService.getValuesDouble(PhmUtil.toShortUUID(unitId), key).toArray());
				//return (List)hyperService.getValuesDouble(unitId, key);
				//return (new ArrayList<Object>(hyperService.getValuesDouble(unitId, key)));
			} else if (valueType.equals("LONG")) {
				return Arrays.asList(hyperService.getValuesLong(PhmUtil.toShortUUID(unitId), key).toArray());
				//return (new ArrayList<Object>(hyperService.getValuesLong(unitId, key)));
			} else if (valueType.equals("STRING")) {
				return Arrays.asList(hyperService.getValuesString(PhmUtil.toShortUUID(unitId), key).toArray());
				//return (new ArrayList<Object>(hyperService.getValuesString(unitId, key)));
			} else if (valueType.equals("BOOL")) {
				return Arrays.asList(hyperService.getValuesBoolean(PhmUtil.toShortUUID(unitId), key).toArray());
				//return (new ArrayList<Object>(hyperService.getValuesBoolean(unitId, key)));
			}
		} catch (Exception e) {
			throw handleException(e);
		}
		return null;
	}

	// сохранение всех данных Unit в файл CSV
	@PostMapping(value = "/csv{unitId}")
	public void saveToCSV(@PathVariable("unitId") String unitId) throws PhmException {
		try {
			hyperService.saveToCSV(PhmUtil.toShortUUID(unitId));
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	// сохранение всех данных Unit в файл Parquet
	@PostMapping(value = "/parquet{unitId}")
	public void saveToParquet(@PathVariable("unitId") String unitId) throws PhmException {
		try {
			hyperService.saveToParquet(PhmUtil.toShortUUID(unitId));
		} catch (Exception e) {
			throw handleException(e);
		}
	}

}
