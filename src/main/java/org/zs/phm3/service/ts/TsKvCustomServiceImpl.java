package org.zs.phm3.service.ts;

import org.zs.phm3.repository.ts.TsKvCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
@Service
public class TsKvCustomServiceImpl implements TsKvCustomService {

    @Autowired
    TsKvCustomRepository tsKvCustomRepository;
    @Value("${global.date_time_format}")
    private String dateTimeFormatPattern;

    @Override
    public List<String> getAllUnitIdWithTsData() {
        List<String> unitIds = new ArrayList<>();
        List<Object[]> idsDateList = tsKvCustomRepository.getAllUnitIdWithTsData();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimeFormatPattern);

        idsDateList.forEach(item -> {

                    String dateTs = LocalDateTime.ofInstant(Instant.ofEpochMilli(Long.parseLong(item[1].toString())),
                            TimeZone.getDefault().toZoneId()).format(formatter).toString();

                    unitIds.add((item[0].toString()) + ", DateTime: " + dateTs);
                    System.out.println((item[0].toString()) + ", DateTime: " + dateTs);

                    System.out.println((item[0]));
                }
        );
        return unitIds;
    }
}
