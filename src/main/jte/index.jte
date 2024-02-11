@import org.example.hexlet.dto.MainPage
@param MainPage page

@template.layout.page(content = @`
    <main>
        <h1>Привет, Хекслет!</h1>
        @if(page.getCurrentUser() != null)
            Добро пожаловать, ${page.getCurrentUser()}.
            Чтобы разлогиниться, удалите куку JSESSIONID из браузера
        @else
            <a href="/sessions/build">Login</a>
        @endif
    </main>
`)

