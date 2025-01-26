package com.mgmingenieriayproyectos.nomina_empleados.service;

//import com.mgmingenieriayproyectos.nomina_empleados.model.Employee;
import com.mgmingenieriayproyectos.nomina_empleados.model.Project;
import com.mgmingenieriayproyectos.nomina_empleados.repository.ProjectRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class AttendanceService {

    private final ProjectRepository projectRepository;

//    private final EmployeeRepository employeeRepository;

    public AttendanceService(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }


    public void processAttendanceFile(MultipartFile file) throws IOException {
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0 || row.getRowNum() == 1) continue; // Saltar encabezados

                String employeeId = row.getCell(0).getStringCellValue();
                System.out.println("employeeId: " + employeeId);

//                Employee employee = employeeRepository.findByDocument(employeeId);
//                System.out.println(employee.getIdentification());

                String name = row.getCell(1).getStringCellValue();
                System.out.println("name: " + name);

                String stringDate = row.getCell(2).getStringCellValue();
                LocalDate date = LocalDate.parse(stringDate);
                System.out.println("date: " + date);

                String stringTime = row.getCell(3).getStringCellValue();
                LocalTime time = LocalTime.parse(stringTime);
                System.out.println("time: " + time);

                String recordOrigin = row.getCell(4).getStringCellValue();
                System.out.println("recordOrigin: " + recordOrigin);

                Project project = projectRepository.findByName(recordOrigin);
                System.out.println("nombre:" + project.getName());

//                BiometricRecord attendance = new BiometricRecord(, name, date, time, recordOrigin, project);
//                attendanceRecordRepository.save(attendance);

            }
        } catch (IOException e) {
            throw new IOException("Error al procesar el archivo Excel", e);
        }
    }
}
