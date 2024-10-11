package org.zs.phm3.controller;
/**
 * Copyright © 2019-2020 The CETC PHM Authors
 *
*/

import java.util.List;

import org.zs.phm3.models.ts.TsKeyAllValue;
import org.zs.phm3.models.ts.TsKeyValue;
import org.zs.phm3.repository.ts.ext.DataType;
import org.zs.phm3.repository.ts.ext.DataUtil;
import org.zs.phm3.repository.ts.ext.DoubleValueEntry;
import org.zs.phm3.util.mapping.PhmUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zs.phm3.exception.PhmException;
//import org.zs.phm3.repository.ts.ext.LongValueEntry;
import org.zs.phm3.service.ts.AggregationService;


@RestController
@RequestMapping("/api/aggregation")
public class TsKvAggregationController extends BaseController {

	@Autowired
	private AggregationService aggregationService;
	
	// получение списка всех агрегированных значений для Unit
	// unitId - идентификатор Unit
	// strartTs - начало периода
	// endTs - конец периода
	// возврат: список ключи-значения
	@GetMapping(value = "/{unitId}")
	public List<TsKeyAllValue> getAll(@PathVariable("unitId") String unitId,
                                      @RequestParam(name = "strartTs", required = false) Long startTs,
                                      @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			if (startTs != null && startTs > 0L) {
				return aggregationService.findAll(PhmUtil.toShortUUID(unitId), startTs, endTs);
			} else {
				return aggregationService.findAll(PhmUtil.toShortUUID(unitId));
			}
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	// получение всех агрегированных значений для Unit по одному ключу
	// unitId - идентификатор Unit
	// key - ключ атрибута
	// strartTs - начало периода
	// endTs - конец периода
	// возврат: ключ-значение
	@GetMapping(value = "/{unitId}/{key}")
	public TsKeyAllValue getByKey(@PathVariable("unitId") String unitId,
								  @PathVariable("key") String key,
								  @RequestParam(name = "strartTs", required = false) Long startTs,
								  @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			if (startTs != null && startTs > 0L) {
				return aggregationService.findOne(PhmUtil.toShortUUID(unitId), key, startTs, endTs);
			} else {
				return aggregationService.findOne(PhmUtil.toShortUUID(unitId), key);
			}
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	// получение списка последних значений (тип Double) для Unit
	// unitId - идентификатор Unit
	// strartTs - начало периода
	// endTs - конец периода
	// возврат: список ключ-значение
	@GetMapping(value = "/{unitId}/kv/last")
	public List<DoubleValueEntry> getLastKvDouble(@PathVariable("unitId") String unitId,
                                                  @RequestParam(name = "strartTs", required = false) Long startTs,
                                                  @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			if (startTs != null && startTs > 0L) {
				return DataUtil.toList(DoubleValueEntry.class, aggregationService.getLastKeysValues(PhmUtil.toShortUUID(unitId), startTs, endTs, DataType.DOUBLE));
			} else {
				return DataUtil.toList(DoubleValueEntry.class, aggregationService.getLastKeysValues(PhmUtil.toShortUUID(unitId), DataType.DOUBLE));
			}
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	// получение максимального значения (тип Double) для Unit по одному ключу
	// unitId - идентификатор Unit
	// key - ключ атрибута
	// strartTs - начало периода
	// endTs - конец периода
	// возврат: значение
	@GetMapping(value = "/{unitId}/{key}/values/max")
	public Double getMaxValues(@PathVariable("unitId") String unitId,
							   @PathVariable("key") String key,
							   @RequestParam(name = "strartTs", required = false) Long startTs,
							   @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			if (startTs != null && startTs > 0L) {
				return (Double)aggregationService.getMaxValue(PhmUtil.toShortUUID(unitId), key, startTs, endTs, DataType.DOUBLE);
			} else {
				return (Double)aggregationService.getMaxValue(PhmUtil.toShortUUID(unitId), key, DataType.DOUBLE);
			}
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	// получение минимального значения (тип Double) для Unit по одному ключу
	// unitId - идентификатор Unit
	// key - ключ атрибута
	// strartTs - начало периода
	// endTs - конец периода
	// возврат: значение
	@GetMapping(value = "/{unitId}/{key}/values/min")
	public Double getMinValues(@PathVariable("unitId") String unitId,
							   @PathVariable("key") String key,
							   @RequestParam(name = "strartTs", required = false) Long startTs,
							   @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			if (startTs != null && startTs > 0L) {
				return (Double)aggregationService.getMinValue(PhmUtil.toShortUUID(unitId), key, startTs, endTs, DataType.DOUBLE);
			} else {
				return (Double)aggregationService.getMinValue(PhmUtil.toShortUUID(unitId), key, DataType.DOUBLE);
			}
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	// получение среднего значения (тип Double) для Unit по одному ключу
	// unitId - идентификатор Unit
	// key - ключ атрибута
	// strartTs - начало периода
	// endTs - конец периода
	// возврат: значение
	@GetMapping(value = "/{unitId}/{key}/values/avg")
	public Double getAvgValues(@PathVariable("unitId") String unitId,
							   @PathVariable("key") String key,
							   @RequestParam(name = "strartTs", required = false) Long startTs,
							   @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			if (startTs != null && startTs > 0L) {
				return (Double)aggregationService.getAvgValue(PhmUtil.toShortUUID(unitId), key, startTs, endTs, DataType.DOUBLE);
			} else {
				return (Double)aggregationService.getAvgValue(PhmUtil.toShortUUID(unitId), key, DataType.DOUBLE);
			}
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	// получение суммы значений (тип Double) для Unit по одному ключу
	// unitId - идентификатор Unit
	// key - ключ атрибута
	// strartTs - начало периода
	// endTs - конец периода
	// возврат: значение
	@GetMapping(value = "/{unitId}/{key}/values/sum")
	public Double getSumValues(@PathVariable("unitId") String unitId,
							   @PathVariable("key") String key,
							   @RequestParam(name = "strartTs", required = false) Long startTs,
							   @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			if (startTs != null && startTs > 0L) {
				return (Double)aggregationService.getSumValue(PhmUtil.toShortUUID(unitId), key, startTs, endTs, DataType.DOUBLE);
			} else {
				return (Double)aggregationService.getSumValue(PhmUtil.toShortUUID(unitId), key, DataType.DOUBLE);
			}
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	// получение последнего значения (тип Double) для Unit по одному ключу
	// unitId - идентификатор Unit
	// key - ключ атрибута
	// strartTs - начало периода
	// endTs - конец периода
	// возврат: значение
	@GetMapping(value = "/{unitId}/{key}/values/last")
	public Double getLastValues(@PathVariable("unitId") String unitId,
							    @PathVariable("key") String key,
							    @RequestParam(name = "strartTs", required = false) Long startTs,
							    @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			if (startTs != null && startTs > 0L) {
				return (Double)aggregationService.getLastValue(PhmUtil.toShortUUID(unitId), key, startTs, endTs, DataType.DOUBLE);
			} else {
				return (Double)aggregationService.getLastValue(PhmUtil.toShortUUID(unitId), key, DataType.DOUBLE);
			}
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	// получение первого значения (тип Double) для Unit по одному ключу
	// unitId - идентификатор Unit
	// key - ключ атрибута
	// strartTs - начало периода
	// endTs - конец периода
	// возврат: значение
	@GetMapping(value = "/{unitId}/{key}/values/first")
	public Double getFirstValues(@PathVariable("unitId") String unitId,
							     @PathVariable("key") String key,
							     @RequestParam(name = "strartTs", required = false) Long startTs,
							     @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			if (startTs != null && startTs > 0L) {
				return (Double)aggregationService.getFirstValue(PhmUtil.toShortUUID(unitId), key, startTs, endTs, DataType.DOUBLE);
			} else {
				return (Double)aggregationService.getFirstValue(PhmUtil.toShortUUID(unitId), key, DataType.DOUBLE);
			}
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	// получение списка дельт значений для Unit по одному ключу
	// unitId - идентификатор Unit
	// key - ключ атрибута
	// strartTs - начало периода
	// endTs - конец периода
	// возврат: список ключ-значение
	@GetMapping(value = "/{unitId}/{key}/values/delta")
	public List<TsKeyValue> getDeltaKvDouble(@PathVariable("unitId") String unitId,
                                             @PathVariable("key") String key,
                                             @RequestParam(name = "strartTs", required = false) Long startTs,
                                             @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			if (startTs != null && startTs > 0L) {
				return aggregationService.findDeltaKeysValues(PhmUtil.toShortUUID(unitId), key, startTs, endTs, DataType.DOUBLE);
			} else {
				return aggregationService.findDeltaKeysValues(PhmUtil.toShortUUID(unitId), key, DataType.DOUBLE);
			}
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	
	
}
