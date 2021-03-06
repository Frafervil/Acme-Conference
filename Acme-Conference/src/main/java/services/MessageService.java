
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MessageRepository;
import domain.Actor;
import domain.Conference;
import domain.Message;
import domain.Registration;
import domain.Submission;

@Service
@Transactional
public class MessageService {

	// Managed Repository

	@Autowired
	private MessageRepository	messageRepository;

	// Supporting services

	@Autowired
	private ActorService		actorService;

	@Autowired
	private SubmissionService	submissionService;

	@Autowired
	private RegistrationService	registrationService;

	@Autowired
	private AuthorService		authorService;


	// Simple CRUD methods

	public Message create() {
		Message result;
		Actor principal;
		Collection<Actor> recipients;

		principal = this.actorService.findByPrincipal();
		Assert.notNull(principal);

		result = new Message();
		result.setSender(principal);
		recipients = new ArrayList<Actor>();
		result.setRecipients(recipients);
		result.setMoment(new Date(System.currentTimeMillis() - 1));

		return result;
	}

	public Message save(final Message message) {
		Message result;
		Actor principal;

		Assert.notNull(message);

		principal = this.actorService.findByPrincipal();
		Assert.notNull(principal);

		Assert.isTrue(message.getSender().equals(principal));

		result = this.messageRepository.save(message);
		Assert.notNull(result);

		return result;
	}

	public Message findOne(final int messageId) {
		Message result;

		result = this.messageRepository.findOne(messageId);
		Assert.notNull(result);
		return result;
	}

	public Collection<Message> findByRecipientId(final int actorId) {
		Collection<Message> result;

		result = this.messageRepository.findByRecipientId(actorId);
		Assert.notNull(result);
		return result;
	}

	public Collection<Message> findBySenderId(final int actorId) {
		Collection<Message> result;

		result = this.messageRepository.findBySenderId(actorId);
		Assert.notNull(result);
		return result;
	}

	public Collection<Message> findById(final int actorId) {
		Collection<Message> result;

		result = this.messageRepository.findById(actorId);
		Assert.notNull(result);
		return result;
	}

	public Collection<Message> findByTopic(final String keyword, final int actorId) {
		Collection<Message> result;

		result = this.messageRepository.findByTopic(keyword, actorId);
		Assert.notNull(result);
		return result;
	}

	public Message broadcast(final Message message) {
		Message result;
		Message saved;
		Actor principal;
		Collection<Actor> recipients;

		Assert.notNull(message);

		principal = this.actorService.findByPrincipal();
		Assert.notNull(principal);
		message.setSender(principal);

		recipients = this.actorService.findAll();
		Assert.notNull(recipients);
		message.setRecipients(recipients);

		saved = this.messageRepository.save(message);
		Assert.notNull(saved);

		result = this.messageRepository.save(saved);
		Assert.notNull(result);

		return result;
	}

	public void deleteInBach(final Collection<Message> messages) {
		this.messageRepository.deleteInBatch(messages);
	}

	public void delete(final Message message) {
		this.messageRepository.delete(message);
	}

	public Message broadcastSubmission(final Message message, final Conference conference) {
		Message result;
		Message saved;
		Actor principal;
		final Collection<Actor> recipients = new ArrayList<Actor>();
		final Collection<Submission> submissions;

		Assert.notNull(message);

		principal = this.actorService.findByPrincipal();
		Assert.notNull(principal);
		message.setSender(principal);

		submissions = this.submissionService.findAllByConferenceId(conference.getId());
		for (final Submission s : submissions)
			recipients.add(s.getAuthor());

		Assert.notNull(recipients);
		message.setRecipients(recipients);

		saved = this.messageRepository.save(message);
		Assert.notNull(saved);

		result = this.messageRepository.save(saved);
		Assert.notNull(result);

		return result;
	}

	public Message broadcastRegistered(final Message message, final Conference conference) {
		Message result;
		Message saved;
		Actor principal;
		final Collection<Actor> recipients = new ArrayList<Actor>();
		final Collection<Registration> registrations;

		Assert.notNull(message);

		principal = this.actorService.findByPrincipal();
		Assert.notNull(principal);
		message.setSender(principal);

		registrations = this.registrationService.findAllByConferenceId(conference.getId());
		for (final Registration r : registrations)
			recipients.add(r.getAuthor());

		Assert.notNull(recipients);
		message.setRecipients(recipients);

		saved = this.messageRepository.save(message);
		Assert.notNull(saved);

		result = this.messageRepository.save(saved);
		Assert.notNull(result);

		return result;
	}

	public Message broadcastAuthors(final Message message) {
		Message result;
		Message saved;
		Actor principal;
		Collection<Actor> recipients;

		Assert.notNull(message);

		principal = this.actorService.findByPrincipal();
		Assert.notNull(principal);
		message.setSender(principal);

		recipients = this.actorService.findAll();
		recipients.retainAll(this.authorService.findAll());

		Assert.notNull(recipients);
		message.setRecipients(recipients);

		Assert.notEmpty(message.getRecipients(), "There are no actors");

		saved = this.messageRepository.save(message);
		Assert.notNull(saved);

		result = this.messageRepository.save(saved);
		Assert.notNull(result);

		return result;
	}
}
