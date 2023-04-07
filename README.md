# udemy-springframework5-REST
Udemy "Spring Framework 5 + REST de cero a experto" course

@Controller -> Exposición de datos / recursos
@Service -> Logica de negocio
@Repository -> Acceso a dato

@Component -> Padre de los anteriores, para cuando no sea uno de los propositos anteriores, simplemente registrar en contexto de springboot

@Primary -> define el bean por defecto para objetos que implementen la misma interfaz (ver Dog.java vs Bird.java)

### LifeCycle

LifeCycleBean Bean name : lifeCycleBean --> (Aware Interfaces)
LifeCycleBean : PostConstruct
LifeCycleBean : afterPropertiesSet

...

LifeCycleBean : PreDestroy
LifeCycleBean : destroy

