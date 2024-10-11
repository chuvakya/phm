package org.zs.phm3;

//import org.zs.phm3.repository.TsKvRepository;


//@RunWith(SpringRunner.class)
//@SpringBootTest
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//public class DataSetCreationTest {
//    @Autowired
//    UnitService unitService;
//    @Autowired
//    private DataSetCreationService dataSetProfileService;
//    @Autowired
//    DatasetRepository datasetRepository;
//
//    @Autowired
//    DatasetService datasetService;
//    @Autowired
//    MlModelService mlModelService;
//
//    private static DataSetCreationHeader hdrWork;
//    private static List<DataSetCreationLine> linesWork = new ArrayList<>();
//    private static Set<DataSetCreationLine> linesWork1 = new HashSet<>();
//
//    @Test
//    public void n6mainProcess() {
//        long start = System.currentTimeMillis() / 1000;
////        String unitIdShortString;
//        String now = "2000-11-09 10:30";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        LocalDateTime formatDateTime = LocalDateTime.parse(now, formatter);
//        List<String> attributesList = new ArrayList<>();
//        String unitIdString = "56db0513-b850-11ea-b332-c5a9ae3f35c3";
//        attributesList.add("U");
//        attributesList.add("I");
//        attributesList.add("Tenv");
//        attributesList.add("Trad");
//        Boolean answer=dataSetProfileService.createDataSet(unitIdString, 1, attributesList,formatDateTime,LocalDateTime.now());
//        long finish = System.currentTimeMillis()/ 1000;
//        System.out.println("Time Data Set Creation (sec): " + (finish - start));
//    }
//
//    @Test
//    public void n7mainProcess() {
//        String unitIdShortString;
//        List<String> attributesList=new ArrayList<>();
//
//        Set<UnitAttributeKey> dataSourceScheme = new HashSet<>();
//        Set<DataSetCreationLine> linesSet = new HashSet<>();
///*        for (int i = 0; i < 5; i++) {
//        }*/
//
//        /* Time */
//        String now = "2000-11-09 10:30";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        LocalDateTime formatDateTime = LocalDateTime.parse(now, formatter);
//        /* Time */
//        String unitIdString = "08e077ff-9ac3-11ea-84ae-f573b5173853";
//        unitIdShortString = UUIDConverter.fromTimeUUID(UUID.fromString(unitIdString));
//        attributesList.add("U");
//        attributesList.add("I");
//        attributesList.add("Tenv");
//        attributesList.add("Trad");
//        dataSetProfileService.createDataSet(unitIdString, 1, attributesList,formatDateTime,LocalDateTime.now());
//
//        unitIdString = "19e464e0-9ac3-11ea-ab8a-5bfe9b114648";
//
//        unitIdShortString = UUIDConverter.fromTimeUUID(UUID.fromString(unitIdString));
//        attributesList.clear();
//        attributesList.add("sensor1");
//        attributesList.add("sensor2");
//        attributesList.add("sensor3");
//
//        Boolean answer=dataSetProfileService.createDataSet(unitIdString, 1, attributesList,formatDateTime,LocalDateTime.now());
//
//    }
//    @Test
//    public void n2showAttributeValues() {
//        String unitIdString = "19e464e0-9ac3-11ea-ab8a-5bfe9b114648";
//        String key="sensor1";
//        String date2000 = "2000-11-09 10:30";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        LocalDateTime formatDateTime = LocalDateTime.parse(date2000, formatter);
//        ArrayList<Double> tsKvFindedList= (ArrayList<Double>) dataSetProfileService.showAttributeValues(unitIdString,key,
//                formatDateTime, LocalDateTime.now(), 15);
//        var a=1;
//    }
////    unitId, timeFrom, timeTo, key
//}
