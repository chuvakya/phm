package org.zs.phm3.controller;
/**
 * Copyright © 2019-2020 The CETC PHM Authors
 *
*/

import java.util.List;

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
import org.zs.phm3.service.ts.ChunkService;

@RestController
@RequestMapping("/api/chunk")
public class TsKvChunkController extends BaseController {

	@Autowired
	private ChunkService chunkService;
	
	
	// interval - milliseconds
	// получение максимальных значений для Unit по всем ключам
	// unitId - идентификатор Unit
	// interval - интервал для чанка (в миллисекундах)
	// strartTs - начало периода
	// endTs - конец периода
	// возврат: список ключ-значение
	@GetMapping(value = "/{unitId}/tskv/max")
	public List<TsKeyValue> getTsKvMax(@PathVariable("unitId") String unitId,
                                       @RequestParam(name = "interval", required = true) Long interval,
                                       @RequestParam(name = "strartTs", required = false) Long startTs,
                                       @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			if (startTs != null && startTs > 0L) {
				return this.chunkService.findMax(PhmUtil.toShortUUID(unitId), startTs, endTs, interval);
			} else {
				return this.chunkService.findMax(PhmUtil.toShortUUID(unitId), interval);
			}
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	// получение максимальных значений для Unit по одному ключу
	// unitId - идентификатор Unit
	// interval - интервал для чанка (в миллисекундах)
	// strartTs - начало периода
	// endTs - конец периода
	// возврат: список ключ-значение
	@GetMapping(value = "/{unitId}/{key}/tskv/max")
	public List<TsKeyValue> getTsKvMaxByKey(@PathVariable("unitId") String unitId,
									        @PathVariable("key") String key,
									        @RequestParam(name = "interval", required = true) Long interval,
									        @RequestParam(name = "strartTs", required = false) Long startTs,
									        @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			if (startTs != null && startTs > 0L) {
				return this.chunkService.findMax(PhmUtil.toShortUUID(unitId), key, startTs, endTs, interval);
			} else {
				return this.chunkService.findMax(PhmUtil.toShortUUID(unitId), key, interval);
			}
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	// получение минимальных значений для Unit по всем ключам
	// unitId - идентификатор Unit
	// interval - интервал для чанка (в миллисекундах)
	// strartTs - начало периода
	// endTs - конец периода
	// возврат: список ключ-значение
	@GetMapping(value = "/{unitId}/tskv/min")
	public List<TsKeyValue> getTsKvMin(@PathVariable("unitId") String unitId,
									   @RequestParam(name = "interval", required = true) Long interval,
									   @RequestParam(name = "strartTs", required = false) Long startTs,
									   @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			if (startTs != null && startTs > 0L) {
				return this.chunkService.findMin(PhmUtil.toShortUUID(unitId), startTs, endTs, interval);
			} else {
				return this.chunkService.findMin(PhmUtil.toShortUUID(unitId), interval);
			}
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	// получение минимальных значений для Unit по одному ключу
	// unitId - идентификатор Unit
	// interval - интервал для чанка (в миллисекундах)
	// strartTs - начало периода
	// endTs - конец периода
	// возврат: список ключ-значение
	@GetMapping(value = "/{unitId}/{key}/tskv/min")
	public List<TsKeyValue> getTsKvMinByKey(@PathVariable("unitId") String unitId,
									        @PathVariable("key") String key,
									        @RequestParam(name = "interval", required = true) Long interval,
									        @RequestParam(name = "strartTs", required = false) Long startTs,
									        @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			if (startTs != null && startTs > 0L) {
				return this.chunkService.findMin(PhmUtil.toShortUUID(unitId), key, startTs, endTs, interval);
			} else {
				return this.chunkService.findMin(PhmUtil.toShortUUID(unitId), key, interval);
			}
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	// получение средних значений для Unit по всем ключам
	// unitId - идентификатор Unit
	// interval - интервал для чанка (в миллисекундах)
	// strartTs - начало периода
	// endTs - конец периода
	// возврат: список ключ-значение
	@GetMapping(value = "/{unitId}/tskv/avg")
	public List<TsKeyValue> getTsKvAvg(@PathVariable("unitId") String unitId,
									   @RequestParam(name = "interval", required = true) Long interval,
									   @RequestParam(name = "strartTs", required = false) Long startTs,
									   @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			if (startTs != null && startTs > 0L) {
				return this.chunkService.findAvg(PhmUtil.toShortUUID(unitId), startTs, endTs, interval);
			} else {
				return this.chunkService.findAvg(PhmUtil.toShortUUID(unitId), interval);
			}
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	// получение средних значений для Unit по одному ключу
	// unitId - идентификатор Unit
	// interval - интервал для чанка (в миллисекундах)
	// strartTs - начало периода
	// endTs - конец периода
	// возврат: список ключ-значение
	@GetMapping(value = "/{unitId}/{key}/tskv/avg")
	public List<TsKeyValue> getTsKvAvgByKey(@PathVariable("unitId") String unitId,
									        @PathVariable("key") String key,
									        @RequestParam(name = "interval", required = true) Long interval,
									        @RequestParam(name = "strartTs", required = false) Long startTs,
									        @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			if (startTs != null && startTs > 0L) {
				return this.chunkService.findAvg(PhmUtil.toShortUUID(unitId), key, startTs, endTs, interval);
			} else {
				return this.chunkService.findAvg(PhmUtil.toShortUUID(unitId), key, interval);
			}
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	// получение сумм значений для Unit по всем ключам
	// unitId - идентификатор Unit
	// interval - интервал для чанка (в миллисекундах)
	// strartTs - начало периода
	// endTs - конец периода
	// возврат: список ключ-значение
	@GetMapping(value = "/{unitId}/tskv/sum")
	public List<TsKeyValue> getTsKvSum(@PathVariable("unitId") String unitId,
									   @RequestParam(name = "interval", required = true) Long interval,
									   @RequestParam(name = "strartTs", required = false) Long startTs,
									   @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			if (startTs != null && startTs > 0L) {
				return this.chunkService.findSum(PhmUtil.toShortUUID(unitId), startTs, endTs, interval);
			} else {
				return this.chunkService.findSum(PhmUtil.toShortUUID(unitId), interval);
			}
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	// получение суммы значений для Unit по одному ключу
	// unitId - идентификатор Unit
	// interval - интервал для чанка (в миллисекундах)
	// strartTs - начало периода
	// endTs - конец периода
	// возврат: список ключ-значение
	@GetMapping(value = "/{unitId}/{key}/tskv/sum")
	public List<TsKeyValue> getTsKvSumByKey(@PathVariable("unitId") String unitId,
									        @PathVariable("key") String key,
									        @RequestParam(name = "interval", required = true) Long interval,
									        @RequestParam(name = "strartTs", required = false) Long startTs,
									        @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			if (startTs != null && startTs > 0L) {
				return this.chunkService.findSum(PhmUtil.toShortUUID(unitId), key, startTs, endTs, interval);
			} else {
				return this.chunkService.findSum(PhmUtil.toShortUUID(unitId), key, interval);
			}
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	// получение количества значений для Unit по всем ключам
	// unitId - идентификатор Unit
	// interval - интервал для чанка (в миллисекундах)
	// strartTs - начало периода
	// endTs - конец периода
	// возврат: список ключ-значение
	@GetMapping(value = "/{unitId}/tskv/count")
	public List<TsKeyValue> getTsKvCount(@PathVariable("unitId") String unitId,
									     @RequestParam(name = "interval", required = true) Long interval,
									     @RequestParam(name = "strartTs", required = false) Long startTs,
									     @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			if (startTs != null && startTs > 0L) {
				return this.chunkService.findCount(PhmUtil.toShortUUID(unitId), startTs, endTs, interval);
			} else {
				return this.chunkService.findCount(PhmUtil.toShortUUID(unitId), interval);
			}
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	// получение количества значений для Unit по одному ключу
	// unitId - идентификатор Unit
	// interval - интервал для чанка (в миллисекундах)
	// strartTs - начало периода
	// endTs - конец периода
	// возврат: список ключ-значение
	@GetMapping(value = "/{unitId}/{key}/tskv/count")
	public List<TsKeyValue> getTsKvCountByKey(@PathVariable("unitId") String unitId,
									        @PathVariable("key") String key,
									        @RequestParam(name = "interval", required = true) Long interval,
									        @RequestParam(name = "strartTs", required = false) Long startTs,
									        @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			if (startTs != null && startTs > 0L) {
				return this.chunkService.findCount(PhmUtil.toShortUUID(unitId), key, startTs, endTs, interval);
			} else {
				return this.chunkService.findCount(PhmUtil.toShortUUID(unitId), key, interval);
			}
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	// получение последних значений для Unit по всем ключам
	// unitId - идентификатор Unit
	// interval - интервал для чанка (в миллисекундах)
	// strartTs - начало периода
	// endTs - конец периода
	// возврат: список ключ-значение
	@GetMapping(value = "/{unitId}/tskv/last")
	public List<TsKeyValue> getTsKvLast(@PathVariable("unitId") String unitId,
									    @RequestParam(name = "interval", required = true) Long interval,
									    @RequestParam(name = "strartTs", required = false) Long startTs,
									    @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			if (startTs != null && startTs > 0L) {
				return this.chunkService.findLast(PhmUtil.toShortUUID(unitId), startTs, endTs, interval);
			} else {
				return this.chunkService.findLast(PhmUtil.toShortUUID(unitId), interval);
			}
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	// получение последних значений для Unit по одному ключу
	// unitId - идентификатор Unit
	// interval - интервал для чанка (в миллисекундах)
	// strartTs - начало периода
	// endTs - конец периода
	// возврат: список ключ-значение
	@GetMapping(value = "/{unitId}/{key}/tskv/last")
	public List<TsKeyValue> getTsKvLastByKey(@PathVariable("unitId") String unitId,
									         @PathVariable("key") String key,
									         @RequestParam(name = "interval", required = true) Long interval,
									         @RequestParam(name = "strartTs", required = false) Long startTs,
									         @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			if (startTs != null && startTs > 0L) {
				return this.chunkService.findLast(PhmUtil.toShortUUID(unitId), key, startTs, endTs, interval);
			} else {
				return this.chunkService.findLast(PhmUtil.toShortUUID(unitId), key, interval);
			}
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	// получение первых значений для Unit по всем ключам
	// unitId - идентификатор Unit
	// interval - интервал для чанка (в миллисекундах)
	// strartTs - начало периода
	// endTs - конец периода
	// возврат: список ключ-значение
	@GetMapping(value = "/{unitId}/tskv/first")
	public List<TsKeyValue> getTsKvFirst(@PathVariable("unitId") String unitId,
									     @RequestParam(name = "interval", required = true) Long interval,
									     @RequestParam(name = "strartTs", required = false) Long startTs,
									     @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			if (startTs != null && startTs > 0L) {
				return this.chunkService.findFirst(PhmUtil.toShortUUID(unitId), startTs, endTs, interval);
			} else {
				return this.chunkService.findFirst(PhmUtil.toShortUUID(unitId), interval);
			}
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	// получение первых значений для Unit по одному ключу
	// unitId - идентификатор Unit
	// interval - интервал для чанка (в миллисекундах)
	// strartTs - начало периода
	// endTs - конец периода
	// возврат: список ключ-значение
	@GetMapping(value = "/{unitId}/{key}/tskv/first")
	public List<TsKeyValue> getTsKvFirstByKey(@PathVariable("unitId") String unitId,
									          @PathVariable("key") String key,
									          @RequestParam(name = "interval", required = true) Long interval,
									          @RequestParam(name = "strartTs", required = false) Long startTs,
									          @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			if (startTs != null && startTs > 0L) {
				return this.chunkService.findFirst(PhmUtil.toShortUUID(unitId), key, startTs, endTs, interval);
			} else {
				return this.chunkService.findFirst(PhmUtil.toShortUUID(unitId), key, interval);
			}
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	// получение списка минимальных значений (тип Double) для Unit по всем ключам
	// unitId - идентификатор Unit
	// interval - интервал для чанка (в миллисекундах)
	// strartTs - начало периода
	// endTs - конец периода
	// возврат: список ключ-значение
	@GetMapping(value = "/{unitId}/kv/min")
	public List<DoubleValueEntry> getMinKvDouble(@PathVariable("unitId") String unitId,
                                                 @RequestParam(name = "interval", required = true) Long interval,
                                                 @RequestParam(name = "strartTs", required = false) Long startTs,
                                                 @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			if (startTs != null && startTs > 0L) {
				return DataUtil.toList(DoubleValueEntry.class, this.chunkService.getMinKeysValues(PhmUtil.toShortUUID(unitId), startTs, endTs, interval, DataType.DOUBLE));
			} else {
				return DataUtil.toList(DoubleValueEntry.class, this.chunkService.getMinKeysValues(PhmUtil.toShortUUID(unitId), interval, DataType.DOUBLE));
			}
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	// получение списка максимальных значений (тип Double) для Unit по всем ключам
	// unitId - идентификатор Unit
	// interval - интервал для чанка (в миллисекундах)
	// strartTs - начало периода
	// endTs - конец периода
	// возврат: список ключ-значение
	@GetMapping(value = "/{unitId}/kv/max")
	public List<DoubleValueEntry> getMaxKvDouble(@PathVariable("unitId") String unitId,
									   			 @RequestParam(name = "interval", required = true) Long interval,
									   			 @RequestParam(name = "strartTs", required = false) Long startTs,
									   			 @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			if (startTs != null && startTs > 0L) {
				return DataUtil.toList(DoubleValueEntry.class, this.chunkService.getMaxKeysValues(PhmUtil.toShortUUID(unitId), startTs, endTs, interval, DataType.DOUBLE));
			} else {
				return DataUtil.toList(DoubleValueEntry.class, this.chunkService.getMaxKeysValues(PhmUtil.toShortUUID(unitId), interval, DataType.DOUBLE));
			}
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	// получение списка средних значений (тип Double) для Unit по всем ключам
	// unitId - идентификатор Unit
	// interval - интервал для чанка (в миллисекундах)
	// strartTs - начало периода
	// endTs - конец периода
	// возврат: список ключ-значение
	@GetMapping(value = "/{unitId}/kv/avg")
	public List<DoubleValueEntry> getAvgKvDouble(@PathVariable("unitId") String unitId,
									   			 @RequestParam(name = "interval", required = true) Long interval,
									   			 @RequestParam(name = "strartTs", required = false) Long startTs,
									   			 @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			if (startTs != null && startTs > 0L) {
				return DataUtil.toList(DoubleValueEntry.class, this.chunkService.getAvgKeysValues(PhmUtil.toShortUUID(unitId), startTs, endTs, interval, DataType.DOUBLE));
			} else {
				return DataUtil.toList(DoubleValueEntry.class, this.chunkService.getAvgKeysValues(PhmUtil.toShortUUID(unitId), interval, DataType.DOUBLE));
			}
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	// получение списка сумм значений (тип Double) для Unit по всем ключам
	// unitId - идентификатор Unit
	// interval - интервал для чанка (в миллисекундах)
	// strartTs - начало периода
	// endTs - конец периода
	// возврат: список ключ-значение
	@GetMapping(value = "/{unitId}/kv/sum")
	public List<DoubleValueEntry> getSumKvDouble(@PathVariable("unitId") String unitId,
									   			 @RequestParam(name = "interval", required = true) Long interval,
									   			 @RequestParam(name = "strartTs", required = false) Long startTs,
									   			 @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			if (startTs != null && startTs > 0L) {
				return DataUtil.toList(DoubleValueEntry.class, this.chunkService.getSumKeysValues(PhmUtil.toShortUUID(unitId), startTs, endTs, interval, DataType.DOUBLE));
			} else {
				return DataUtil.toList(DoubleValueEntry.class, this.chunkService.getSumKeysValues(PhmUtil.toShortUUID(unitId), interval, DataType.DOUBLE));
			}
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	// получение списка количества значений (тип Double) для Unit по всем ключам
	// unitId - идентификатор Unit
	// interval - интервал для чанка (в миллисекундах)
	// strartTs - начало периода
	// endTs - конец периода
	// возврат: список ключ-значение
	@GetMapping(value = "/{unitId}/kv/count")
	public List<DoubleValueEntry> getCountKvDouble(@PathVariable("unitId") String unitId,
									   			   @RequestParam(name = "interval", required = true) Long interval,
									   			   @RequestParam(name = "strartTs", required = false) Long startTs,
									   			   @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			if (startTs != null && startTs > 0L) {
				return DataUtil.toList(DoubleValueEntry.class, this.chunkService.getCountKeysValues(PhmUtil.toShortUUID(unitId), startTs, endTs, interval, DataType.DOUBLE));
			} else {
				return DataUtil.toList(DoubleValueEntry.class, this.chunkService.getCountKeysValues(PhmUtil.toShortUUID(unitId), interval, DataType.DOUBLE));
			}
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	// получение списка последних значений (тип Double) для Unit по всем ключам
	// unitId - идентификатор Unit
	// interval - интервал для чанка (в миллисекундах)
	// strartTs - начало периода
	// endTs - конец периода
	// возврат: список ключ-значение
	@GetMapping(value = "/{unitId}/kv/last")
	public List<DoubleValueEntry> getLastKvDouble(@PathVariable("unitId") String unitId,
									   			  @RequestParam(name = "interval", required = true) Long interval,
									   			  @RequestParam(name = "strartTs", required = false) Long startTs,
									   			  @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			if (startTs != null && startTs > 0L) {
				return DataUtil.toList(DoubleValueEntry.class, this.chunkService.getLastKeysValues(PhmUtil.toShortUUID(unitId), startTs, endTs, interval, DataType.DOUBLE));
			} else {
				return DataUtil.toList(DoubleValueEntry.class, this.chunkService.getLastKeysValues(PhmUtil.toShortUUID(unitId), interval, DataType.DOUBLE));
			}
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	// получение списка первых значений (тип Double) для Unit по всем ключам
	// unitId - идентификатор Unit
	// interval - интервал для чанка (в миллисекундах)
	// strartTs - начало периода
	// endTs - конец периода
	// возврат: список ключ-значение
	@GetMapping(value = "/{unitId}/kv/first")
	public List<DoubleValueEntry> getFirstKvDouble(@PathVariable("unitId") String unitId,
									   			   @RequestParam(name = "interval", required = true) Long interval,
									   			   @RequestParam(name = "strartTs", required = false) Long startTs,
									   			   @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			if (startTs != null && startTs > 0L) {
				return DataUtil.toList(DoubleValueEntry.class, this.chunkService.getFirstKeysValues(PhmUtil.toShortUUID(unitId), startTs, endTs, interval, DataType.DOUBLE));
			} else {
				return DataUtil.toList(DoubleValueEntry.class, this.chunkService.getFirstKeysValues(PhmUtil.toShortUUID(unitId), interval, DataType.DOUBLE));
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
	// возврат: список значений
	@GetMapping(value = "/{unitId}/{key}/values/max")
	public List<Double> getMaxValuesDouble(@PathVariable("unitId") String unitId,
										   @PathVariable("key") String key,
									   	   @RequestParam(name = "interval", required = true) Long interval,
									       @RequestParam(name = "strartTs", required = false) Long startTs,
									   	   @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			if (startTs != null && startTs > 0L) {
				return DataUtil.toList(Double.class, this.chunkService.getMaxValues(PhmUtil.toShortUUID(unitId), key, startTs, endTs, interval, DataType.DOUBLE));
			} else {
				return DataUtil.toList(Double.class, this.chunkService.getMaxValues(PhmUtil.toShortUUID(unitId), key, interval, DataType.DOUBLE));
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
	// возврат: список значений
	@GetMapping(value = "/{unitId}/{key}/values/min")
	public List<Double> getMinValuesDouble(@PathVariable("unitId") String unitId,
										   @PathVariable("key") String key,
									   	   @RequestParam(name = "interval", required = true) Long interval,
									   	   @RequestParam(name = "strartTs", required = false) Long startTs,
									   	   @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			if (startTs != null && startTs > 0L) {
				return DataUtil.toList(Double.class, this.chunkService.getMinValues(PhmUtil.toShortUUID(unitId), key, startTs, endTs, interval, DataType.DOUBLE));
			} else {
				return DataUtil.toList(Double.class, this.chunkService.getMinValues(PhmUtil.toShortUUID(unitId), key, interval, DataType.DOUBLE));
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
	// возврат: список значений
	@GetMapping(value = "/{unitId}/{key}/values/avg")
	public List<Double> getAvgValuesDouble(@PathVariable("unitId") String unitId,
										   @PathVariable("key") String key,
									   	   @RequestParam(name = "interval", required = true) Long interval,
									   	   @RequestParam(name = "strartTs", required = false) Long startTs,
									   	   @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			if (startTs != null && startTs > 0L) {
				return DataUtil.toList(Double.class, this.chunkService.getAvgValues(PhmUtil.toShortUUID(unitId), key, startTs, endTs, interval, DataType.DOUBLE));
			} else {
				return DataUtil.toList(Double.class, this.chunkService.getAvgValues(PhmUtil.toShortUUID(unitId), key, interval, DataType.DOUBLE));
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
	// возврат: список значений
	@GetMapping(value = "/{unitId}/{key}/values/sum")
	public List<Double> getSumValuesDouble(@PathVariable("unitId") String unitId,
										   @PathVariable("key") String key,
									   	   @RequestParam(name = "interval", required = true) Long interval,
									   	   @RequestParam(name = "strartTs", required = false) Long startTs,
									   	   @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			if (startTs != null && startTs > 0L) {
				return DataUtil.toList(Double.class, this.chunkService.getSumValues(PhmUtil.toShortUUID(unitId), key, startTs, endTs, interval, DataType.DOUBLE));
			} else {
				return DataUtil.toList(Double.class, this.chunkService.getSumValues(PhmUtil.toShortUUID(unitId), key, interval, DataType.DOUBLE));
			}
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	// получение списка количества значений (тип Double) для Unit по одному ключу
	// unitId - идентификатор Unit
	// key - ключ атрибута
	// interval - интервал для чанка (в миллисекундах)
	// strartTs - начало периода
	// endTs - конец периода
	// возврат: список значений
	@GetMapping(value = "/{unitId}/{key}/values/count")
	public List<Double> getCountValuesDouble(@PathVariable("unitId") String unitId,
										     @PathVariable("key") String key,
									   	   	 @RequestParam(name = "interval", required = true) Long interval,
									   	   	 @RequestParam(name = "strartTs", required = false) Long startTs,
									   	     @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			if (startTs != null && startTs > 0L) {
				return DataUtil.toList(Double.class, this.chunkService.getCountValues(PhmUtil.toShortUUID(unitId), key, startTs, endTs, interval, DataType.DOUBLE));
			} else {
				return DataUtil.toList(Double.class, this.chunkService.getCountValues(PhmUtil.toShortUUID(unitId), key, interval, DataType.DOUBLE));
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
	// возврат: список значений
	@GetMapping(value = "/{unitId}/{key}/values/last")
	public List<Double> getLastValuesDouble(@PathVariable("unitId") String unitId,
										    @PathVariable("key") String key,
									   	   	@RequestParam(name = "interval", required = true) Long interval,
									   	   	@RequestParam(name = "strartTs", required = false) Long startTs,
									   	    @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			if (startTs != null && startTs > 0L) {
				return DataUtil.toList(Double.class, this.chunkService.getLastValues(PhmUtil.toShortUUID(unitId), key, startTs, endTs, interval, DataType.DOUBLE));
			} else {
				return DataUtil.toList(Double.class, this.chunkService.getLastValues(PhmUtil.toShortUUID(unitId), key, interval, DataType.DOUBLE));
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
	// возврат: список значений
	@GetMapping(value = "/{unitId}/{key}/values/first")
	public List<Double> getFirstValuesDouble(@PathVariable("unitId") String unitId,
										     @PathVariable("key") String key,
									   	   	 @RequestParam(name = "interval", required = true) Long interval,
									   	   	 @RequestParam(name = "strartTs", required = false) Long startTs,
									   	     @RequestParam(name = "endTs", required = false) Long endTs) throws PhmException {
		try {
			if (startTs != null && startTs > 0L) {
				return DataUtil.toList(Double.class, this.chunkService.getFirstValues(PhmUtil.toShortUUID(unitId), key, startTs, endTs, interval, DataType.DOUBLE));
			} else {
				return DataUtil.toList(Double.class, this.chunkService.getFirstValues(PhmUtil.toShortUUID(unitId), key, interval, DataType.DOUBLE));
			}
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	
}
