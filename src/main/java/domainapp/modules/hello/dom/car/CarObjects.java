package domainapp.modules.hello.dom.car;

import java.util.Collections;
import java.util.List;

import org.apache.causeway.applib.annotation.Action;
import org.apache.causeway.applib.annotation.ActionLayout;
import org.apache.causeway.applib.annotation.DomainService;
import org.apache.causeway.applib.annotation.PriorityPrecedence;
import org.apache.causeway.applib.annotation.PromptStyle;
import org.apache.causeway.applib.annotation.RestrictTo;
import org.apache.causeway.applib.annotation.SemanticsOf;
import org.apache.causeway.applib.services.repository.RepositoryService;

import domainapp.modules.hello.types.Name;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named("hello.CarObjects")
@DomainService
@Priority(PriorityPrecedence.EARLY)
public class CarObjects {

    private final RepositoryService repositoryService;

    @Inject
    public CarObjects(
            final RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    @Action(semantics = SemanticsOf.NON_IDEMPOTENT)
    @ActionLayout(promptStyle = PromptStyle.DIALOG_MODAL)
    public CarObject create(@Name final String name, final String type) {
        var p = new CarObject(name, type);
        return repositoryService.persist(p);
    }
    public String default0Create() {
        return "Hello Car!";
    }


    @Action(semantics = SemanticsOf.SAFE)
    @ActionLayout(promptStyle = PromptStyle.DIALOG_SIDEBAR)
    public List<CarObject> findByName(@Name final String name) {
        return Collections.emptyList();
    }

    @Action(semantics = SemanticsOf.SAFE, restrictTo = RestrictTo.PROTOTYPING)
    public List<CarObject> listAll() {
        return Collections.emptyList();
    }


}
