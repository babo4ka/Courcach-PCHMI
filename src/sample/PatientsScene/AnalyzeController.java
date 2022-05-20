package sample.PatientsScene;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.DatePicker;
import sample.Districts;
import sample.Patient;

import java.time.Instant;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Stream;

public class AnalyzeController {

    @FXML
    private DatePicker start_dates;
    @FXML
    private DatePicker end_dates;


    @FXML
    private PieChart analyze_chart;

    private List<Date> recieps = new ArrayList();

    private Map<String, PieChart.Data> slices = new HashMap<>();

    @FXML
    private void analyze(){
        slices.clear();
        for(Districts d: Districts.values()){
            slices.put(d.toString(), new PieChart.Data(d.toString(), 0));
        }

        for(Patient p : Patient.getPatients()){
            recieps.addAll(p.getRecieps());
            slices.get(p.getDistrict().toString()).setPieValue(slices.get(p.getDistrict().toString()).getPieValue()+p.getRecieps().size());
        }

        if(start_dates.getValue() != null){
            Date startDate = Date.from(Instant.from(start_dates.getValue().atStartOfDay(ZoneId.systemDefault())));
            Stream<Date> result = Stream.of(recieps.toArray(new Date[0])).filter(s-> s.getTime() >= startDate.getTime());
            recieps.clear();
            result.forEach(s->recieps.add(s));
        }
        if(end_dates.getValue() != null){
            Date endDate = Date.from(Instant.from(end_dates.getValue().atStartOfDay(ZoneId.systemDefault())));
            Stream<Date> result = Stream.of(recieps.toArray(new Date[0])).filter(s-> s.getTime() <= endDate.getTime());
            recieps.clear();
            result.forEach(s->recieps.add(s));
        }

        analyze_chart.getData().clear();
        Stream<PieChart.Data> result = Stream.of(slices.values().toArray(new PieChart.Data[0])).filter(s->s.getPieValue()!=0);
        result.forEach(s->analyze_chart.getData().add(s));

        for(Date r: recieps){
                System.out.println(r);
        }
    }

}
