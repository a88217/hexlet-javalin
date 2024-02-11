@import org.example.hexlet.dto.courses.CoursePage
@import org.example.hexlet.utils.NamedRoutes
@param CoursePage page

@template.layout.page(content = @`

    <form action="${NamedRoutes.userPath(page.getCourse().getId())}" method="patch">
        <div>
            <label>
                Название
                <input type="text" name="name" value="${page.getCourse().getName()}" />
            </label>
        </div>
        <div>
            <label>
                Описание
                <input type="text" name="description" value="${page.getCourse().getDescription()}" />
            </label>
        </div>

        <input type="submit" value="Обновить" />
    </form>
`)