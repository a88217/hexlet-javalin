
@import org.example.hexlet.dto.courses.CoursePage
@import org.example.hexlet.utils.NamedRoutes
@param CoursePage page

@template.layout.page(content = @`
    <main>
        <h1>${page.getCourse().getName()}</h1>
        <p>${page.getCourse().getDescription()}</p>
    </main>
    <a href="${NamedRoutes.editCoursePath(page.getCourse().getId())}">Update course</a>
`)

