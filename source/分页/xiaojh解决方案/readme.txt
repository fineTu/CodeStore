Page可以根据自己的pageNumber属性自动修正页小于0或大于pageNumber的翻页请求。
在action中查询分页的列表时
	1。从表中查询出当前的分页信息，得到一个与当前翻页请求对应的page对象
	2。从表中查询与page对应的列表信息，得到一个list对象
	3。压入request中待jsp使用
service层需要一个返回page对象的方法，核心是一条count语句；和一个返回list的查询方法。
///////////////////////////////////////////////////////////////////

///////////////by江圣///////////////////
@Action(value = MY_ANNOUNCEMENTS, results = {@Result(name = SUCCESS, location = FOREPART
			+ MY_ANNOUNCEMENTS + JSP)})
	@Auth(type = UserWithClubMemberAuth.class)
	public String myAnnouncements()
	{
		if(page == null)
		{
			page = announcementService.clubAnnouncementsPage(
					super.currentUser(), true);
		}
		super.getRequestMap().put(
				MY_ANNOUNCEMENTS,
				announcementService.clubAnnouncements(super.currentUser(),
						page, true));
		return SUCCESS;
	}
	
//////////////by fineTu/////////////////
@Action(value = "merchantFocus", results = {@Result(name = SUCCESS, location = BaseAction.FOREPART
			+ "merchantFocus.jsp")})
	@Auth(auths = {AuthEnum.MERCHANT})
	public String merchantFocus()
	{
		// List<User> focusList = sessionUser.getUsersForTargetUserId();
		Merchant m = null;
		if(merchant != null)
		{
			m = merchant;
		}
		else
		{
			m = (Merchant) getSessionMap().get("merchant");
		}
		Page page = new Page(EACH_PAGE_NUM, pageNum * EACH_PAGE_NUM + 1);
		page.setCurrentPage(pageNum);
		Page itemPage = null;
		switch(type)
		{
			case (1):
				ClubList focusClubs = new ClubList();
				itemPage = merchantService.getFocusNum(Club.class, m, page);
				focusClubs.setPage(itemPage);
				List<Club> clubList = merchantService.getFocusList(Club.class,
						m, itemPage);
				focusClubs.setClubList(clubList);
				getRequestMap().put("focusList", focusClubs);
				break;
			case (2):
				MerchantList focusMerchants = new MerchantList();
				itemPage = merchantService.getFocusNum(Merchant.class, m, page);
				focusMerchants.setPage(itemPage);
				List<Merchant> merchantList = merchantService.getFocusList(
						Merchant.class, m, itemPage);
				focusMerchants.setMerchantList(merchantList);
				
				getRequestMap().put("focusList",focusMerchants);
				break;
		}
		return SUCCESS;
	}
////////////////////////////////////////////////////////////////////

