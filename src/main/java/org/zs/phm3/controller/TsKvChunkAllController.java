package org.zs.phm3.controller;
/**
 * Copyright © 2019-2020 The CETC PHM Authors
 *
*/

import java.util.List;

import org.zs.phm3.models.ts.TsKeyAllValue;
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
import org.zs.phm3.service.ts.ChunkAllService;


@RestController
@RequestMapping("/api/chunk/all")
public class TsKvChunkAllController extends BaseController {

	@Autowired
	private ChunkAllService chunkAllService;
	

	// interval - milliseconds
	// получение списка всех агрегированных значений для Unit по одному ключу
	// unitId - идентификатор Unit
	// key - ключ атрибута
	// interval - интервал для чанка (в миллисекундах)
	// strartTs - начало периода
	// endTs - конец периода
	// возврат: список ключ-значение
	@GetMapping(value = "/{unitId}/{key}")
	public List<TsKeyAllValue> getAll(@PathVariable("unitId") String unitId,
                                      @PathVariable("key") String key,
                                      @RequestParam(name = "interval", required = true) Long interval,
                                      @RequestParam(name = "strartTs", required = false) Long startTs,
                                      @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			if (startTs != null && startTs > 0L) {
				return this.chunkAllService.findAll(PhmUtil.toShortUUID(unitId), key, startTs, endTs, interval);
			} else {
				return this.chunkAllService.findAll(PhmUtil.toShortUUID(unitId), key, interval);
			}
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	// получение списка последних значений (тип Double) для Unit по одному ключу
	// unitId - идентификатор Unit
	// key - ключ атрибута
	// interval - интервал для чанка (в миллисекундах)
	// strartTs - начало периода
	// endTs - конец периода
	// возврат: список ключ-значение
	@GetMapping(value = "/{unitId}/{key}/kv/last")
	public List<DoubleValueEntry> getLastKv(@PathVariable("unitId") String unitId,
                                            @PathVariable("key") String key,
                                            @RequestParam(name = "interval", required = true) Long interval,
                                            @RequestParam(name = "strartTs", required = false) Long startTs,
                                            @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			if (startTs != null && startTs > 0L) {
				return DataUtil.toList(DoubleValueEntry.class, this.chunkAllService.findLastKeysValues(PhmUtil.toShortUUID(unitId), key, startTs, endTs, interval, DataType.DOUBLE));
			} else {
				return DataUtil.toList(DoubleValueEntry.class, this.chunkAllService.findLastKeysValues(PhmUtil.toShortUUID(unitId), key, interval, DataType.DOUBLE));
			}
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	// получение списка первых значений (тип Double) для Unit по одному ключу
	// unitId - идентификатор Unit
	// key - ключ атрибута
	// interval - интервал для чанка (в миллисекундах)
	// strartTs - начало периода
	// endTs - конец периода
	// возврат: список ключ-значение
	@GetMapping(value = "/{unitId}/{key}/kv/first")
	public List<DoubleValueEntry> getFirstKv(@PathVariable("unitId") String unitId,
									         @PathVariable("key") String key,
									         @RequestParam(name = "interval", required = true) Long interval,
									         @RequestParam(name = "strartTs", required = false) Long startTs,
									         @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			if (startTs != null && startTs > 0L) {
				return DataUtil.toList(DoubleValueEntry.class, this.chunkAllService.findFirstKeysValues(PhmUtil.toShortUUID(unitId), key, startTs, endTs, interval, DataType.DOUBLE));
			} else {
				return DataUtil.toList(DoubleValueEntry.class, this.chunkAllService.findFirstKeysValues(PhmUtil.toShortUUID(unitId), key, interval, DataType.DOUBLE));
			}
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	// получение списка максимальных значений (тип Double) для Unit по одному ключу
	// unitId - идентификатор Unit
	// key - ключ атрибута
	// interval - интервал для чанка (в миллисекундах)
	// strartTs - начало периода
	// endTs - конец периода
	// возврат: список ключ-значение
	@GetMapping(value = "/{unitId}/{key}/kv/max")
	public List<DoubleValueEntry> getMaxKv(@PathVariable("unitId") String unitId,
									       @PathVariable("key") String key,
									       @RequestParam(name = "interval", required = true) Long interval,
									       @RequestParam(name = "strartTs", required = false) Long startTs,
									       @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			if (startTs != null && startTs > 0L) {
				return DataUtil.toList(DoubleValueEntry.class, this.chunkAllService.findMaxKeysValues(PhmUtil.toShortUUID(unitId), key, startTs, endTs, interval, DataType.DOUBLE));
			} else {
				return DataUtil.toList(DoubleValueEntry.class, this.chunkAllService.findMaxKeysValues(PhmUtil.toShortUUID(unitId), key, interval, DataType.DOUBLE));
			}
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	// получение списка минимальных значений (тип Double) для Unit по одному ключу
	// unitId - идентификатор Unit
	// key - ключ атрибута
	// interval - интервал для чанка (в миллисекундах)
	// strartTs - начало периода
	// endTs - конец периода
	// возврат: список ключ-значение
	@GetMapping(value = "/{unitId}/{key}/kv/min")
	public List<DoubleValueEntry> getMinKv(@PathVariable("unitId") String unitId,
									       @PathVariable("key") String key,
									       @RequestParam(name = "interval", required = true) Long interval,
									       @RequestParam(name = "strartTs", required = false) Long startTs,
									       @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			if (startTs != null && startTs > 0L) {
				return DataUtil.toList(DoubleValueEntry.class, this.chunkAllService.findMinKeysValues(PhmUtil.toShortUUID(unitId), key, startTs, endTs, interval, DataType.DOUBLE));
			} else {
				return DataUtil.toList(DoubleValueEntry.class, this.chunkAllService.findMinKeysValues(PhmUtil.toShortUUID(unitId), key, interval, DataType.DOUBLE));
			}
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	// получение списка сумм значений (тип Double) для Unit по одному ключу
	// unitId - идентификатор Unit
	// key - ключ атрибута
	// interval - интервал для чанка (в миллисекундах)
	// strartTs - начало периода
	// endTs - конец периода
	// возврат: список ключ-значение	
	@GetMapping(value = "/{unitId}/{key}/kv/sum")
	public List<DoubleValueEntry> getSumKv(@PathVariable("unitId") String unitId,
									       @PathVariable("key") String key,
									       @RequestParam(name = "interval", required = true) Long interval,
									       @RequestParam(name = "strartTs", required = false) Long startTs,
									       @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			if (startTs != null && startTs > 0L) {
				return DataUtil.toList(DoubleValueEntry.class, this.chunkAllService.findSumKeysValues(PhmUtil.toShortUUID(unitId), key, startTs, endTs, interval, DataType.DOUBLE));
			} else {
				return DataUtil.toList(DoubleValueEntry.class, this.chunkAllService.findSumKeysValues(PhmUtil.toShortUUID(unitId), key, interval, DataType.DOUBLE));
			}
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	// получение списка средних значений (тип Double) для Unit по одному ключу
	// unitId - идентификатор Unit
	// key - ключ атрибута
	// interval - интервал для чанка (в миллисекундах)
	// strartTs - начало периода
	// endTs - конец периода
	// возврат: список ключ-значение
	@GetMapping(value = "/{unitId}/{key}/kv/avg")
	public List<DoubleValueEntry> getAvgKv(@PathVariable("unitId") String unitId,
									       @PathVariable("key") String key,
									       @RequestParam(name = "interval", required = true) Long interval,
									       @RequestParam(name = "strartTs", required = false) Long startTs,
									       @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			if (startTs != null && startTs > 0L) {
				return DataUtil.toList(DoubleValueEntry.class, this.chunkAllService.findAvgKeysValues(PhmUtil.toShortUUID(unitId), key, startTs, endTs, interval, DataType.DOUBLE));
			} else {
				return DataUtil.toList(DoubleValueEntry.class, this.chunkAllService.findAvgKeysValues(PhmUtil.toShortUUID(unitId), key, interval, DataType.DOUBLE));
			}
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	
}
