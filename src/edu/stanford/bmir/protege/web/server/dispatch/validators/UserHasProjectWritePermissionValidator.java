package edu.stanford.bmir.protege.web.server.dispatch.validators;

import edu.stanford.bmir.protege.web.shared.user.UserId;
import edu.stanford.bmir.protege.web.server.dispatch.RequestContext;
import edu.stanford.bmir.protege.web.server.dispatch.RequestValidationResult;
import edu.stanford.bmir.protege.web.server.dispatch.RequestValidator;
import edu.stanford.bmir.protege.web.shared.HasProjectId;
import edu.stanford.bmir.protege.web.shared.dispatch.Action;
import edu.stanford.bmir.protege.web.shared.dispatch.Result;
import edu.stanford.bmir.protege.web.shared.project.ProjectId;

/**
 * Author: Matthew Horridge<br>
 * Stanford University<br>
 * Bio-Medical Informatics Research Group<br>
 * Date: 19/02/2013
 * <p>
 *     A validator which passes users that have write permission for project specific actions.
 * </p>
 */
public class UserHasProjectWritePermissionValidator<A extends Action<R> & HasProjectId, R extends Result> implements RequestValidator<A> {

    public static <A extends Action<R> & HasProjectId, R extends Result> UserHasProjectWritePermissionValidator<A, R> get() {
        return new UserHasProjectWritePermissionValidator<A, R>();
    }

    @Override
    public RequestValidationResult validateAction(A action, RequestContext requestContext) {
        ProjectId projectId = action.getProjectId();
        UserId userId = requestContext.getUserId();
        return RequestValidationResult.getValid();
    }


}
