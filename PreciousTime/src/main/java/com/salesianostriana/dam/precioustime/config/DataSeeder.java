package com.salesianostriana.dam.precioustime.config;

import com.salesianostriana.dam.precioustime.category.model.Category;
import com.salesianostriana.dam.precioustime.category.repository.CategoryRepository;
import com.salesianostriana.dam.precioustime.preference.model.NotificationType;
import com.salesianostriana.dam.precioustime.preference.model.Preference;
import com.salesianostriana.dam.precioustime.preference.model.Theme;
import com.salesianostriana.dam.precioustime.preference.repository.PreferenceRepository;
import com.salesianostriana.dam.precioustime.project.model.Project;
import com.salesianostriana.dam.precioustime.project.model.ProjectStatus;
import com.salesianostriana.dam.precioustime.project.repository.ProjectRepository;
import com.salesianostriana.dam.precioustime.reminder.model.Reminder;
import com.salesianostriana.dam.precioustime.reminder.repository.ReminderRepository;
import com.salesianostriana.dam.precioustime.task.model.Priority;
import com.salesianostriana.dam.precioustime.task.model.Task;
import com.salesianostriana.dam.precioustime.task.model.TaskStatus;
import com.salesianostriana.dam.precioustime.task.repository.TaskRepository;
import com.salesianostriana.dam.precioustime.user.model.User;
import com.salesianostriana.dam.precioustime.user.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class  DataSeeder {

    private final CategoryRepository categoryRepository;
    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final ReminderRepository reminderRepository;
    private final PreferenceRepository preferenceRepository;
    private final UserRepository userRepository;

    @PostConstruct
    public void init() {

        if (categoryRepository.count() > 0) return;

        User user = userRepository.findByUsername("user").orElse(null);

        Category trabajo = categoryRepository.save(Category.builder()
                .name("Trabajo")
                .emoji("💼")
                .color("#3B82F6")
                .build());

        Category salud = categoryRepository.save(Category.builder()
                .name("Salud")
                .emoji("🏃")
                .color("#22C55E")
                .build());

        Category estudio = categoryRepository.save(Category.builder()
                .name("Estudio")
                .emoji("📚")
                .color("#A855F7")
                .build());

        Category hogar = categoryRepository.save(Category.builder()
                .name("Hogar")
                .emoji("🏠")
                .color("#F97316")
                .build());

        Category finanzas = categoryRepository.save(Category.builder()
                .name("Finanzas")
                .emoji("💰")
                .color("#EAB308")
                .build());

        Project appMovil = projectRepository.save(Project.builder()
                .name("App Móvil")
                .description("Desarrollo de una aplicación móvil para gestión de tiempo personal.")
                .finishDate(LocalDate.of(2026, 6, 30))
                .status(ProjectStatus.EN_PROCESO)
                .progress(new BigDecimal("40.00"))
                .author("user")
                .build());

        Project renovacionHogar = projectRepository.save(Project.builder()
                .name("Renovación del hogar")
                .description("Planificación y ejecución de reformas en el apartamento.")
                .finishDate(LocalDate.of(2026, 5, 15))
                .status(ProjectStatus.EN_PROCESO)
                .progress(new BigDecimal("20.00"))
                .author("user")
                .build());

        Project cursoOnline = projectRepository.save(Project.builder()
                .name("Curso online de Java")
                .description("Completar el curso avanzado de Spring Boot en Udemy.")
                .finishDate(LocalDate.of(2026, 4, 1))
                .status(ProjectStatus.COMPLETADO)
                .progress(new BigDecimal("100.00"))
                .author("user")
                .build());

        Task tarea1 = Task.builder()
                .title("Diseñar pantallas de inicio")
                .description("Crear los mockups de la pantalla principal en Figma.")
                .status(TaskStatus.COMPLETADO)
                .priority(Priority.ALTA)
                .completedAt(LocalDateTime.of(2026, 2, 10, 10, 0))
                .category(trabajo)
                .author("user")
                .build();
        appMovil.addTask(tarea1);
        taskRepository.save(tarea1);

        Task tarea2 = Task.builder()
                .title("Implementar autenticación JWT")
                .description("Configurar Spring Security con JWT en el backend.")
                .status(TaskStatus.PENDIENTE)
                .priority(Priority.ALTA)
                .completedAt(LocalDateTime.of(2026, 3, 20, 12, 0))
                .category(trabajo)
                .author("user")
                .build();
        appMovil.addTask(tarea2);
        taskRepository.save(tarea2);

        Task tarea3 = Task.builder()
                .title("Comprar materiales de pintura")
                .description("Adquirir rodillos, pintura y cinta de carrocero.")
                .status(TaskStatus.PENDIENTE)
                .priority(Priority.MEDIA)
                .completedAt(LocalDateTime.of(2026, 3, 25, 9, 0))
                .category(hogar)
                .author("user")
                .build();
        renovacionHogar.addTask(tarea3);
        taskRepository.save(tarea3);

        Task tarea4 = Task.builder()
                .title("Completar módulo de Hibernate")
                .description("Ver los vídeos del módulo 7 y hacer los ejercicios.")
                .status(TaskStatus.COMPLETADO)
                .priority(Priority.MEDIA)
                .completedAt(LocalDateTime.of(2026, 1, 15, 18, 30))
                .category(estudio)
                .author("user")
                .build();
        cursoOnline.addTask(tarea4);
        taskRepository.save(tarea4);

        Task tarea5 = Task.builder()
                .title("Revisión médica anual")
                .description("Pedir cita con el médico de cabecera para el chequeo anual.")
                .status(TaskStatus.PENDIENTE)
                .priority(Priority.ALTA)
                .completedAt(LocalDateTime.of(2026, 4, 5, 11, 0))
                .category(salud)
                .author("user")
                .build();
        taskRepository.save(tarea5);

        Task tarea6 = Task.builder()
                .title("Revisar declaración de la renta")
                .description("Preparar los documentos y revisar el borrador con el gestor.")
                .status(TaskStatus.PENDIENTE)
                .priority(Priority.ALTA)
                .completedAt(LocalDateTime.of(2026, 4, 30, 10, 0))
                .category(finanzas)
                .author("user")
                .build();
        taskRepository.save(tarea6);

        projectRepository.save(appMovil);
        projectRepository.save(renovacionHogar);
        projectRepository.save(cursoOnline);

        preferenceRepository.save(Preference.builder()
                .notificationsActive(true)
                .theme(Theme.DARK)
                .type(NotificationType.IN_APP)
                .author("user")
                .build());

        if (user != null) {
            reminderRepository.save(Reminder.builder()
                    .title("¡Reunión de equipo!")
                    .message("No olvides la reunión de equipo esta tarde a las 17:00.")
                    .read(false)
                    .receiver(user)
                    .build());

            reminderRepository.save(Reminder.builder()
                    .title("Entrega del proyecto")
                    .message("La entrega del módulo de autenticación es mañana.")
                    .read(false)
                    .receiver(user)
                    .build());

            reminderRepository.save(Reminder.builder()
                    .title("Cita médica")
                    .message("Recuerda que tienes cita con el médico el próximo lunes.")
                    .read(true)
                    .receiver(user)
                    .build());
        }
    }

}
