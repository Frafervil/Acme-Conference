package controllers.any;

import java.util.ArrayList;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ConferenceCommentService;
import services.ConferenceService;
import services.SponsorshipService;
import services.PanelService;
import services.PresentationService;
import services.TutorialService;
import controllers.AbstractController;
import domain.Conference;
import domain.Sponsorship;
import domain.ConferenceComment;
import domain.Panel;
import domain.Presentation;
import domain.Tutorial;

@Controller
@RequestMapping("/conference")
public class ConferenceController extends AbstractController {

	// Services

	@Autowired
	private ConferenceService conferenceService;

	@Autowired
	private SponsorshipService	sponsorshipService;
	@Autowired
	private ConferenceCommentService conferenceCommentService;


	@Autowired
	private TutorialService tutorialService;

	@Autowired
	private PanelService panelService;

	@Autowired
	private PresentationService presentationService;

	// List

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false) final String keyword, @RequestParam(required = false, defaultValue = "false") final Boolean keywordBool, @RequestParam(required = false) final Double fee,
		@RequestParam(required = false) final String category, @RequestParam(required = false) final String minDate, @RequestParam(required = false) final String maxDate) {
		ModelAndView result;
		Collection<Conference> conferences;

		if (keywordBool && keyword != "") {
			conferences = this.conferenceService.findByKeyword(keyword);

			if (category != "") {
				Collection<Conference> conferencesByCategory;
				conferencesByCategory = this.conferenceService.searchByCategory(category);
				conferences.retainAll(conferencesByCategory);
			}

			if (fee != null) {
				Collection<Conference> conferencesByMaxFee;
				conferencesByMaxFee = this.conferenceService.searchByMaxFee(fee);
				conferences.retainAll(conferencesByMaxFee);
			}

			if (minDate != "" && maxDate != "") {
				Collection<Conference> conferencesByDateRanges;

				final DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

				Date date1 = null;

				try {
					date1 = format.parse(minDate);
				} catch (final ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Date date2 = null;
				try {
					date2 = format.parse(maxDate);
				} catch (final ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				conferencesByDateRanges = this.conferenceService.searchByDates(date1, date2);
				conferences.retainAll(conferencesByDateRanges);
			}
		}

		else if (keywordBool && fee != null) {
			conferences = this.conferenceService.searchByMaxFee(fee);

			if (category != "") {
				Collection<Conference> conferencesByCategory;
				conferencesByCategory = this.conferenceService.searchByCategory(category);
				conferences.retainAll(conferencesByCategory);
			}

			if (minDate != "" && maxDate != "") {
				Collection<Conference> conferencesByDateRanges;

				final DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

				Date date1 = null;

				try {
					date1 = format.parse(minDate);
				} catch (final ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Date date2 = null;
				try {
					date2 = format.parse(maxDate);
				} catch (final ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				conferencesByDateRanges = this.conferenceService.searchByDates(date1, date2);
				conferences.retainAll(conferencesByDateRanges);
			}
		}

		else if (keywordBool && category != "") {
			conferences = this.conferenceService.searchByCategory(category);

			if (minDate != "" && maxDate != "") {
				Collection<Conference> conferencesByDateRanges;

				final DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

				Date date1 = null;

				try {
					date1 = format.parse(minDate);
				} catch (final ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Date date2 = null;
				try {
					date2 = format.parse(maxDate);
				} catch (final ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				conferencesByDateRanges = this.conferenceService.searchByDates(date1, date2);
				conferences.retainAll(conferencesByDateRanges);
			}
		}

		else if (keywordBool && minDate != "" && maxDate != "") {
			final DateFormat format = new SimpleDateFormat("dd/MM/yyyy");

			Date date1 = null;

			try {
				date1 = format.parse(minDate);
			} catch (final ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Date date2 = null;
			try {
				date2 = format.parse(maxDate);
			} catch (final ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			conferences = this.conferenceService.searchByDates(date1, date2);

		} else
			conferences = this.conferenceService.findFinals();

		result = new ModelAndView("conference/list");
		result.addObject("conferences", conferences);
		result.addObject("requestURI", "conference/list.do");

		return result;
	}

	// List of forthcoming conferences

	@RequestMapping(value = "/listForthcoming", method = RequestMethod.GET)
	public ModelAndView listForthcoming(
			@RequestParam(required = false) final String keyword,
			@RequestParam(required = false, defaultValue = "false") final Boolean keywordBool) {
		ModelAndView result;
		Collection<Conference> conferences;

		if (keywordBool && keyword != null)
			conferences = this.conferenceService
					.findFinalForthcomingByKeyword(keyword);
		else
			conferences = this.conferenceService.findFinalForthcoming();

		result = new ModelAndView("conference/list");
		result.addObject("conferences", conferences);
		result.addObject("requestURI", "conference/list.do");

		return result;
	}

	// List of past conferences

	@RequestMapping(value = "/listPast", method = RequestMethod.GET)
	public ModelAndView listPast(
			@RequestParam(required = false) final String keyword,
			@RequestParam(required = false, defaultValue = "false") final Boolean keywordBool) {
		ModelAndView result;
		Collection<Conference> conferences;

		if (keywordBool && keyword != null)
			conferences = this.conferenceService
					.findFinalPastByKeyword(keyword);
		else
			conferences = this.conferenceService.findFinalPast();

		result = new ModelAndView("conference/list");
		result.addObject("conferences", conferences);
		result.addObject("requestURI", "conference/list.do");

		return result;
	}

	// List of running conferences

	@RequestMapping(value = "/listRunning", method = RequestMethod.GET)
	public ModelAndView listRunning(
			@RequestParam(required = false) final String keyword,
			@RequestParam(required = false, defaultValue = "false") final Boolean keywordBool) {
		ModelAndView result;
		Collection<Conference> conferences;

		if (keywordBool && keyword != null)
			conferences = this.conferenceService
					.findFinalRunningByKeyword(keyword);
		else
			conferences = this.conferenceService.findFinalRunning();

		result = new ModelAndView("conference/list");
		result.addObject("conferences", conferences);
		result.addObject("requestURI", "conference/list.do");

		return result;
	}

	// Display

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int conferenceId) {
		// Inicializa resultado
		ModelAndView result;
		Conference conference;
		boolean submissionDeadlineOver = false;
		Date date = new Date(System.currentTimeMillis());
		List<Sponsorship> sponsorships;
		Random r;
		Sponsorship sponsorship;
		final Collection<ConferenceComment> conferenceComments;
		final Collection<Tutorial> tutorials;
		final Collection<Panel> panels;
		final Collection<Presentation> presentations;

		
		// Busca en el repositorio
		conference = this.conferenceService.findOne(conferenceId);
		Assert.notNull(conference);
		
		if(conference.getSubmissionDeadline().before(date)){
			submissionDeadlineOver = true;
		}
	

		sponsorships = new ArrayList<>(this.sponsorshipService.findAll());
		r = new Random();
		sponsorship = sponsorships.get(r.nextInt(sponsorships.size()));

		conferenceComments = this.conferenceCommentService
				.findAllByConference(conferenceId);

		tutorials = this.tutorialService.findByConferenceId(conferenceId);
		panels = this.panelService.findByConferenceId(conferenceId);
		presentations = this.presentationService
				.findByConferenceId(conferenceId);

		// Crea y a�ade objetos a la vista
		result = new ModelAndView("conference/display");
		result.addObject("requestURI", "conference/display.do");
		result.addObject("conferenceComments", conferenceComments);
		result.addObject("tutorials", tutorials);
		result.addObject("panels", panels);
		result.addObject("presentations", presentations);
		result.addObject("conference", conference);
		result.addObject("submissionDeadlineOver", submissionDeadlineOver);
		result.addObject("banner", sponsorship.getBanner());

		// Env�a la vista
		return result;
	}
}
