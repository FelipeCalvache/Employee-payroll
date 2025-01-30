package com.mgmingenieriayproyectos.nomina_empleados.service;

import com.mgmingenieriayproyectos.nomina_empleados.model.Employee;
import com.mgmingenieriayproyectos.nomina_empleados.repository.EmployeeRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class EmployeeService {

    private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void saveEployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public String loadEmployeeBioFile(MultipartFile file) throws IOException {
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            int cantEmployeeLoaded = 0;
            for (Row row : sheet) {
                if (row.getRowNum() == 0 || row.getRowNum() == 1) continue; // Saltar encabezados

                String employeeId = row.getCell(0).getStringCellValue();
                if (!employeeRepository.existsByIdentification(employeeId)) {
                    String name = row.getCell(1).getStringCellValue();
                    Employee employee = new Employee(name, employeeId, "123", "EMPLOYEE");
                    employeeRepository.save(employee);
                    cantEmployeeLoaded++;
                }
            }
            return Integer.toString(cantEmployeeLoaded);

        } catch (IOException e) {
            throw new IOException("Error al procesar el archivo Excel", e);
        }
    }

    public Optional<Employee> findByIdentification(String identification) {
        Optional<Employee> employee = employeeRepository.findByIdentification(identification);
        if (employee.isEmpty()) {
            log.warn("No se pudo procesar el registro: Empleado={} o Proyecto={} no encontrado",
                    identification, employee);
        }
        return employee;
    }
}
