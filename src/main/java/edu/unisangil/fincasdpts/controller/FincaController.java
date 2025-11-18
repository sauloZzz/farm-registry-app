package edu.unisangil.fincasdpts.controller;

import edu.unisangil.fincasdpts.entity.Departamento;
import edu.unisangil.fincasdpts.entity.Finca;
import edu.unisangil.fincasdpts.entity.Municipio;
import edu.unisangil.fincasdpts.repository.DepartamentoRepository;
import edu.unisangil.fincasdpts.repository.FincaRepository;
import edu.unisangil.fincasdpts.repository.MunicipioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class FincaController {

    private final FincaRepository fincaRepository;
    private final DepartamentoRepository departamentoRepository;
    private final MunicipioRepository municipioRepository;

    public FincaController(FincaRepository fincaRepository,
                           DepartamentoRepository departamentoRepository,
                           MunicipioRepository municipioRepository) {
        this.fincaRepository = fincaRepository;
        this.departamentoRepository = departamentoRepository;
        this.municipioRepository = municipioRepository;
    }

    // Pantalla principal
    @GetMapping({"/fincas"})
    public String mostrarFincas(Model model) {

        model.addAttribute("fincas", fincaRepository.findAll());
        model.addAttribute("departamentos", departamentoRepository.findAll());
        model.addAttribute("finca", new Finca());

        return "fincas"; // fincas.html
    }

    // Guardar finca
    @PostMapping("/fincas/guardar")
    public String guardarFinca(
            @ModelAttribute Finca finca,
            @RequestParam("departamentoId") Long departamentoId,
            @RequestParam("municipioId") Long municipioId,
            Model model
    ) {
        try {
            Departamento dpto = departamentoRepository.findById(departamentoId)
                    .orElseThrow(() -> new IllegalArgumentException("Departamento no encontrado"));

            Municipio muni = municipioRepository.findById(municipioId)
                    .orElseThrow(() -> new IllegalArgumentException("Municipio no encontrado"));

            finca.setDepartamento(dpto);
            finca.setMunicipio(muni);

            fincaRepository.save(finca);

            model.addAttribute("exito", "Finca registrada correctamente");

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Error al guardar la finca: " + e.getMessage());
        }

        return "redirect:/fincas";
    }

    // Eliminar finca por ID
    @GetMapping("/fincas/eliminar/{id}")
    public String eliminarFinca(@PathVariable Long id) {
        fincaRepository.deleteById(id);
        return "redirect:/fincas";
    }

    // Municipios por departamento
    @GetMapping("/municipios/{idDepartamento}")
    @ResponseBody
    public List<Municipio> obtenerMunicipiosPorDepartamento(@PathVariable Long idDepartamento) {
        return municipioRepository.findByDepartamento_Id(idDepartamento);
    }
}
