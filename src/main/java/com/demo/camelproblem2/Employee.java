package com.demo.camelproblem2;

import lombok.Data;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

import java.time.LocalDate;

@Data
@CsvRecord(separator = ",")
public class Employee {
  @DataField(pos = 1) private long employeeId;
  @DataField(pos = 2, trim = true) private String departmentId;
  @DataField(pos = 3, pattern = "yyyy-MM-dd") private LocalDate fromDate;
  @DataField(pos = 4, pattern = "yyyy-MM-dd") private LocalDate toDate;
}
