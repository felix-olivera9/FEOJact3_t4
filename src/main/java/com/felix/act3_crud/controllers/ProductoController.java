package com.felix.act3_crud.controllers;

import com.felix.act3_crud.entities.Categoria;
import com.felix.act3_crud.entities.Producto;
import com.felix.act3_crud.services.CategoriaService;
import com.felix.act3_crud.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    // 1. READ: Listar productos y categorías
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("productos", productoService.listarProductos());
        model.addAttribute("categorias", categoriaService.listarCategorias());
        model.addAttribute("productoForm", new Producto());
        return "productos"; // Vista HTML
    }

    // 2. CREATE / UPDATE: Guardar o actualizar producto
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Producto producto) {
        productoService.guardarProducto(producto);
        return "redirect:/productos";
    }

    // 3. EDIT: Cargar datos para modificar
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Producto producto = productoService.obtenerPorId(id).orElse(null);
        model.addAttribute("productoForm", producto);
        model.addAttribute("productos", productoService.listarProductos());
        model.addAttribute("categorias", categoriaService.listarCategorias());
        return "productos";
    }

    // 4. DELETE: Eliminar producto
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return "redirect:/productos";
    }
}