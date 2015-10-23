package org.earelin.ecclesia

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class OrganizationController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Organization.list(params), model:[organizationCount: Organization.count()]
    }

    def show(Organization organization) {
        respond organization
    }

    def create() {
        respond new Organization(params)
    }

    @Transactional
    def save(Organization organization) {
        if (organization == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (organization.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond organization.errors, view:'create'
            return
        }

        organization.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'organization.label', default: 'Organization'), organization.id])
                redirect organization
            }
            '*' { respond organization, [status: CREATED] }
        }
    }

    def edit(Organization organization) {
        respond organization
    }

    @Transactional
    def update(Organization organization) {
        if (organization == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (organization.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond organization.errors, view:'edit'
            return
        }

        organization.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'organization.label', default: 'Organization'), organization.id])
                redirect organization
            }
            '*'{ respond organization, [status: OK] }
        }
    }

    @Transactional
    def delete(Organization organization) {

        if (organization == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        organization.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'organization.label', default: 'Organization'), organization.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'organization.label', default: 'Organization'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
