package io.github.lcnicolau.cs50.todolist.tasks;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
class TaskInitializer {

    private final TaskRepository taskRepository;

    TaskInitializer(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @PostConstruct
    void init() {
        taskRepository.saveAll(getDummyTasks().toList());
    }

    static Stream<Task> getDummyTasks() {
        return Stream.of(
                "Lorem ipsum dolor sit amet, consectetur adipisicing elit.",
                "Accusamus accusantium aut earum omnis placeat. Est, maxime?",
                "Culpa esse expedita nesciunt obcaecati quae temporibus vero.",
                "Consequatur dolorem ducimus mollitia porro, soluta unde ut!",
                "Ad reprehenderit rerum sed! Dolorem fugiat neque odio!",
                "Ad, aut eligendi harum modi necessitatibus sit voluptate.",
                "Adipisci corporis ducimus ex nisi saepe unde velit!",
                "Consequatur dolorum eveniet id ipsum nihil quo, voluptatem!",
                "Exercitationem harum iure laboriosam placeat quas quisquam soluta!",
                "Consequatur harum laudantium maxime, natus neque quos rerum!",
                "Libero maiores nam omnis ratione sed voluptate voluptatum.",
                "Alias atque cum iure obcaecati quidem quo sunt.",
                "Eum hic iste, minima omnis quisquam sequi ullam?",
                "Dolor eaque hic itaque molestias quod repellendus voluptate.",
                "Blanditiis eligendi obcaecati, provident quisquam reiciendis voluptatem voluptatibus.",
                "Corporis eveniet explicabo iusto minima molestiae quas quo!",
                "Accusantium fugiat labore obcaecati quasi soluta tenetur voluptates.",
                "A aspernatur ipsa iusto minima nam, nihil voluptatem.",
                "Deleniti dignissimos ex id, magnam magni repudiandae sunt?",
                "Explicabo, modi molestias nihil provident quam repellat totam!",
                "Dolorum ducimus laudantium modi rerum tempora velit, voluptatum!",
                "Aliquam, cumque enim magnam nemo nisi qui quis!",
                "Et incidunt itaque laudantium numquam qui, quod rem.",
                "Assumenda autem facilis molestias mollitia nemo sit totam.",
                "Ipsa perspiciatis rem voluptatem? Commodi dolorem omnis similique?",
                "Assumenda aut corporis, cum facere id laborum placeat!",
                "Debitis et quaerat quisquam saepe? Beatae, dolorem quasi?",
                "Accusantium delectus eos iusto rerum suscipit temporibus tenetur.",
                "A aspernatur est excepturi minus possimus repellendus voluptas?",
                "A accusantium architecto neque nihil quos reiciendis, ut.",
                "Ab dolor illum natus quia sapiente ut voluptatem.",
                "A, consequuntur enim ipsum ratione recusandae sequi soluta.",
                "Architecto, delectus necessitatibus quasi qui quibusdam tempora veritatis?",
                "A in provident quaerat qui saepe sit, vitae!",
                "Assumenda beatae consequatur itaque nostrum officia qui veniam!",
                "Accusantium amet animi debitis doloribus illo officiis sint.",
                "A accusamus alias culpa deserunt odit optio sapiente.",
                "Animi assumenda, distinctio in incidunt nesciunt quaerat sequi.",
                "Alias, cupiditate distinctio doloremque mollitia non nostrum perferendis.",
                "Animi distinctio illo iure laboriosam tenetur. Officiis, sint?",
                "Alias corporis praesentium quis quo sapiente sint voluptatibus!",
                "At debitis laudantium rem vel. Aspernatur eligendi, possimus!",
                "Accusantium aliquid consequuntur officia omnis, porro provident repellendus!",
                "Animi consequatur inventore reiciendis sint! Iste, quia, ratione?",
                "Aut consequatur dicta dolor ipsum necessitatibus. Perspiciatis, unde.",
                "Atque eaque facilis laboriosam molestias quibusdam reiciendis repellendus?",
                "Dignissimos doloribus excepturi fugit qui sunt, veritatis voluptatum.",
                "Ad, asperiores doloremque error nostrum qui reiciendis tempore!",
                "Asperiores atque cumque distinctio ex laboriosam nobis repellendus!",
                "Aut, deserunt dolores fugit nemo non reiciendis temporibus?",
                "Atque doloribus, ducimus et praesentium sapiente temporibus vel!",
                "Accusamus, amet dolorem! Aliquid at consequatur porro reiciendis.",
                "Corporis error, pariatur quaerat sunt tempore tenetur velit?",
                "Architecto aut blanditiis consectetur ea expedita quisquam saepe.",
                "Ab at beatae minima, officia quam quo recusandae?",
                "At distinctio eius facilis modi non nostrum voluptas!",
                "Adipisci animi hic nobis possimus quam quidem tempora.",
                "Blanditiis, doloremque id perspiciatis quisquam saepe sequi sint.",
                "Architecto incidunt labore, omnis sint sunt veniam voluptatem?",
                "Commodi eaque est facere molestiae quae quod sint.",
                "Commodi cumque magni odit officia placeat quo, vel.",
                "Architecto aspernatur deleniti ea expedita laudantium nemo unde!",
                "At dicta dolores, ea excepturi iusto maiores quidem.",
                "Culpa cupiditate impedit ipsa omnis, possimus quaerat similique?",
                "Aperiam eaque eius error quos reprehenderit. Dolore, voluptas?",
                "Animi, id, neque. Asperiores consequuntur id illo nobis!",
                "Asperiores eos labore quia voluptas voluptatum? Accusamus, quidem.",
                "A accusamus ad cupiditate eveniet quos ratione unde.",
                "Cumque dolorum est et, excepturi obcaecati perferendis voluptas!",
                "Dolorum error fugit nostrum sequi sit tenetur voluptatum?",
                "Amet laborum modi necessitatibus omnis rem! Laboriosam, quasi.",
                "Aperiam ipsum quibusdam repellendus repudiandae sequi voluptates? Beatae.",
                "Aliquam dicta dolores est quisquam quo temporibus vero.",
                "Aspernatur consequatur magni quae qui quidem sapiente ullam?",
                "Autem deleniti facere illo, iusto quibusdam ratione sequi.",
                "Aspernatur deleniti dolore expedita id laborum modi necessitatibus.",
                "A doloremque illum quae tempore voluptatum. Facilis, magnam.",
                "Aliquid dolore eum ex itaque maxime temporibus unde.",
                "Accusantium corporis eveniet nemo nihil optio tempora voluptates?",
                "Debitis incidunt maiores, maxime nulla placeat repellat rerum!",
                "Amet at, cupiditate in ipsum laborum neque quam.",
                "Adipisci eligendi ipsam itaque sed, voluptates voluptatibus. Dolor?",
                "Aliquam consectetur cum excepturi ipsa nihil quis vitae.",
                "Delectus laboriosam molestias odit officiis perferendis quam, ratione?",
                "Accusamus aliquid dignissimos, id nostrum officiis quis sapiente.",
                "Alias dignissimos illum natus officiis sint vel voluptas?",
                "Adipisci alias animi ducimus fugiat, optio ut voluptas.",
                "Dicta earum hic inventore nobis recusandae repellat, sapiente!",
                "Alias, consequuntur ducimus iste minus officiis quasi vero.",
                "A aliquam asperiores deserunt eligendi laborum placeat reiciendis?",
                "Ab aliquid assumenda cumque est libero rem vitae.",
                "At autem deserunt eaque ex magni obcaecati voluptatum.",
                "Dolorem eos esse id libero nisi odio repellendus.",
                "Consectetur id quisquam sapiente veniam! Earum, iusto, rerum.",
                "Cumque deleniti ex perspiciatis provident quasi quia sit.",
                "Deserunt eum libero nemo nostrum numquam repellat rerum?",
                "Aliquid exercitationem magni possimus quibusdam suscipit velit voluptates?",
                "Assumenda cupiditate laboriosam sunt ullam vero! Possimus, velit!",
                "Dignissimos dolorem facilis soluta veritatis! Corporis, quas, repellat.",
                "Debitis distinctio facilis in ipsa laboriosam perferendis quam?"
        ).map(Task::new);
    }

}
