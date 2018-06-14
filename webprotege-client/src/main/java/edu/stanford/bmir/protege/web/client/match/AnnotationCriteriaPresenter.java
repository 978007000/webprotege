package edu.stanford.bmir.protege.web.client.match;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import edu.stanford.bmir.protege.web.shared.entity.OWLAnnotationPropertyData;
import edu.stanford.bmir.protege.web.shared.match.criteria.*;
import org.semanticweb.owlapi.model.IRI;

import javax.annotation.Nonnull;
import javax.inject.Inject;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 13 Jun 2018
 */
public class AnnotationCriteriaPresenter implements CriteriaPresenter {

    @Nonnull
    private final AnnotationCriteriaView view;

    @Nonnull
    private final AnnotationValueCriteriaPresenter valuePresenter;

    @Inject
    public AnnotationCriteriaPresenter(@Nonnull AnnotationCriteriaView view, @Nonnull AnnotationValueCriteriaPresenter valuePresenter) {
        this.view = checkNotNull(view);
        this.valuePresenter = checkNotNull(valuePresenter);
    }

    @Override
    public void start(@Nonnull AcceptsOneWidget container) {
        container.setWidget(view);
        valuePresenter.start(view.getValueCriteriaViewContainer());
    }

    @Override
    public void stop() {

    }

    @Override
    public Optional<Criteria> getCriteria() {
        AnnotationPropertyCriteria propertyCriteria = view.getSelectedProperty()
                                                          .map(prop -> (AnnotationPropertyCriteria) IriEqualsCriteria.get(prop))
                                                          .orElse(AnyAnnotationPropertyCriteria.get());
        Optional<Criteria> criteria = valuePresenter.getCriteria();
        if(!criteria.isPresent()) {
            return Optional.empty();
        }
        AnnotationValueCriteria valueCriteria = (AnnotationValueCriteria) criteria.get();
        AnnotationCriteria annotationCriteria = AnnotationCriteria.get(
                propertyCriteria,
                valueCriteria,
                AnyAnnotationSetCriteria.get(),
                view.getAnnotationPresence()
        );
        return Optional.of(annotationCriteria);
    }
}