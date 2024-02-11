@import org.example.hexlet.dto.users.UserPage
@import org.example.hexlet.utils.NamedRoutes
@param UserPage page

@template.layout.page(content = @`

    <form action="${NamedRoutes.userPath(page.getUser().getId())}" method="patch">
        <div>
            <label>
                Имя
                <input type="text" name="name" value="${page.getUser().getName()}" />
            </label>
        </div>
        <div>
            <label>
                Email
                <input type="email" name="email" value="${page.getUser().getEmail()}" />
            </label>
        </div>
        <div>
            <label>
                Пароль
                <input type="password" name="password" />
            </label>
        </div>
        <div>
            <label>
                Подтверждение пароля
                <input type="password" name="passwordConfirmation" />
            </label>
        </div>
        <input type="submit" value="Обновить" />
    </form>
`)