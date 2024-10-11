package org.zs.phm3.controller;
/**
 * Copyright © 2019-2020 The CETC PHM Authors
 *
*/

import org.zs.phm3.models.ts.TsKvLatest;
import org.zs.phm3.util.mapping.PhmUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zs.phm3.exception.PhmException;
import org.zs.phm3.service.ts.TsKvLatestService;

import java.util.List;

@RestController
@RequestMapping("/api/telemetry/latest")
public class TsKvLatestTelemetryController extends BaseController {

	@Autowired
	private TsKvLatestService latestService;

	// Latest data
	// получение последних данных для Unit
	@GetMapping(value = "/{unitId}")
	public List<TsKvLatest> getLatest(@PathVariable("unitId") String unitId) throws PhmException {
		try {
			return latestService.findAllByEntityId(PhmUtil.toShortUUID(unitId));
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	// получение последних данных для Unit по ключу
	@GetMapping(value = "/{unitId}/{key}")
	public List<TsKvLatest> getLatest(@PathVariable("unitId") String unitId, @PathVariable("key") String key) throws PhmException {
		try {
			return latestService.findAllByEntityIdAndKey(PhmUtil.toShortUUID(unitId), key);
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	// сохранение последних данных для Unit
	@PostMapping(value = "/{unitId}")
	public void saveLatest(@PathVariable("unitId") String unitId) throws PhmException {
		try {
			latestService.saveLatest(PhmUtil.toShortUUID(unitId));
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	// сохранение последних данных для Unit по ключу
	@PostMapping(value = "/{unitId}/{key}")
	public void saveLatest(@PathVariable("unitId") String unitId, @PathVariable("key") String key) throws PhmException {
		try {
			latestService.saveLatest(PhmUtil.toShortUUID(unitId), key);
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	// удаление последних данных для Unit
	@DeleteMapping(value = "/{unitId}")
	public void deleteLatest(@PathVariable("unitId") String unitId) throws PhmException {
		try {
			latestService.deleteByEntityId(PhmUtil.toShortUUID(unitId));
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	// удаление последних данных для Unit по ключу
	@DeleteMapping(value = "/{unitId}/{key}")
	public void deleteLatest(@PathVariable("unitId") String unitId, @PathVariable("key") String key) throws PhmException {
		try {
			latestService.deleteById(PhmUtil.toShortUUID(unitId), key);
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	// переименование ключа для Unit
	// keyOld - старое название
	// keyNew - новое название
	@PutMapping(value = "/{unitId}/{keyOld}/{keyNew}")
	public void renameKeyLatest(@PathVariable("unitId") String unitId,
						  		@PathVariable("keyOld") String keyOld,
						  		@PathVariable("keyNew") String keyNew) throws PhmException {
		try {
			latestService.renameKey(PhmUtil.toShortUUID(unitId), keyOld, keyNew);
		} catch (Exception e) {
			throw handleException(e);
		}
	}
}
