package domainapp.modules.hello.dom.car;

import java.util.Comparator;

import org.apache.causeway.applib.annotation.Action;
import org.apache.causeway.applib.annotation.ActionLayout;
import org.apache.causeway.applib.annotation.DomainObject;
import org.apache.causeway.applib.annotation.DomainObjectLayout;
import org.apache.causeway.applib.annotation.PropertyLayout;
import org.apache.causeway.applib.annotation.SemanticsOf;
import org.apache.causeway.applib.annotation.Title;
import org.apache.causeway.applib.layout.LayoutConstants;
import org.apache.causeway.applib.services.message.MessageService;
import org.apache.causeway.applib.services.repository.RepositoryService;
import org.apache.causeway.applib.services.title.TitleService;
import org.apache.causeway.persistence.jpa.applib.integration.CausewayEntityListener;

import domainapp.modules.hello.types.Name;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
        schema="hello",
        name = "Car",
        uniqueConstraints = {
                @UniqueConstraint(name = "CarObject__name__UNQ", columnNames = {"name"})
        }
)
@EntityListeners(CausewayEntityListener.class) // injection support
@Named("hello.Car")
@DomainObject()
@DomainObjectLayout()  // causes UI events to be triggered
public class CarObject implements Comparable<CarObject> {

    protected CarObject(){}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, name = "id")
    private Long id;

//    @Version
//    @Column(nullable = false, name = "version")
//    private int version;


    public CarObject(final String name, final String type) {
        this.name = name;
        this.type = type;
    }



    @Column(length = Name.MAX_LEN, nullable = false, name = "name")
    private String name;

    @Title(prepend = "Object: ")
    @Name
    @PropertyLayout(fieldSetId = LayoutConstants.FieldSetId.IDENTITY, sequence = "1")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @PropertyLayout(fieldSetId = LayoutConstants.FieldSetId.IDENTITY, sequence = "2")
    @Column(nullable = false, name = "type")
    private String type;

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }


//    @Column(length = Notes.MAX_LEN, nullable = true, name = "notes")
//    private String notes;
//
//    @Notes
//    @PropertyLayout(fieldSetId = LayoutConstants.FieldSetId.DETAILS, sequence = "1")
//    public String getNotes() {
//        return notes;
//    }
//    public void setNotes(String notes) {
//        this.notes = notes;
//    }



//    @Action(
//            semantics = SemanticsOf.IDEMPOTENT,
//            executionPublishing = Publishing.ENABLED
//    )
//    @ActionLayout(
//            associateWith = "name",
//            describedAs = "Updates the object's name"
//    )
//    public PersonObject updateName(
//            @Name final String name) {
//        setName(name);
//        return this;
//    }
//    public String default0UpdateName() {
//        return getName();
//    }



    @Action(
            semantics = SemanticsOf.NON_IDEMPOTENT_ARE_YOU_SURE
    )
    @ActionLayout(
            fieldSetId = LayoutConstants.FieldSetId.IDENTITY,
            describedAs = "Deletes this object from the database",
            position = ActionLayout.Position.PANEL
    )
    public void delete() {
        final String title = titleService.titleOf(this);
        messageService.informUser(String.format("'%s' deleted", title));
        repositoryService.removeAndFlush(this);
    }



    @Override
    public String toString() {
        return getName();
    }

    @Override
    public int compareTo(final CarObject other) {
        return Comparator.comparing(CarObject::getName).compare(this, other);
    }


    @Inject @Transient RepositoryService repositoryService;
    @Inject @Transient TitleService titleService;
    @Inject @Transient MessageService messageService;

}
