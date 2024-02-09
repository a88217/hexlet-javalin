@import org.example.hexlet.dto.courses.CoursesPage
@param CoursesPage page

@template.layout.page(content = @`
    <h1>${page.getHeader()}</h1>
    <div>
        <form action="/courses" method="get">
            <input type="search" name="term" value="${page.getTerm()}" />
            <input type="submit" value="Search" />
        </form>
    </div>
    @if(page.getCourses().isEmpty())
        <p>Пока не добавлено ни одного курса</p>
    @else
        @for(var course : page.getCourses())
            <div>
                <h2><a href="/courses/${course.getId()}">${course.getName()}</a></h2>
                <p>${course.getDescription()}</p>
            </div>
        @endfor
    @endif
`)
