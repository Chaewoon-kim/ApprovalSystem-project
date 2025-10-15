const groupForm = function(result){
		const group = {};
		result.forEach(form => {
			const category = form.formCategory;
			if(!group[category]){
				group[category] = [];
			}
			group[category].push(form);
		});
		return group;
	}
	
	const renderFormList = function(groupdata){
		let content = '';
		Object.entries(groupdata).forEach(([category, formList])=>{
			content += `<div class="menu">
				<div class="menu-title">
				<img src="img/arrow.png">
				<span>${category}</span>
			</div>
			<div class="submenu">`
			formList.forEach(form=>{
				content += `<div class="form" data-formid="${form.formId}">
				<img src="img/file.png">
				<span>${form.formName}</span>
				</div>`
			})
			content += '</div></div>'		
		})
		$('.selectForm').html(content);
	}
	const getForms = function(result){
		const grouped = groupForm(result);
		renderFormList(grouped);
	};
	
	const searchFormTitle = function(keyword){
		$.ajax({
			url: "controller",
			data: {
				cmd: "getForms",
				keyword: keyword 
			},
			dataType: "json",
			success: getForms
		})
	}
	
	$(document).ready(function(){
		$('.make').click(
			function(){
				searchFormTitle(null);
			}
		)
		$('.form-search input').on('keyup', function(e){
			if (e.key === 'Enter' || e.keyCode === 13) {
		        const keyword = $(this).val();
		        searchFormTitle(keyword);
		    }
		})
		$('.selectForm').on('click', '.form', function(){
		    $('.selectForm .form').removeClass('selected');
		    $(this).addClass('selected');
		});
	})
	
	$('.setForm').click(function(){
		const selectFormId = $('.selectForm .form.selected').data('formid')
		window.location.href='controller?cmd=setForm&formId='+selectFormId;
	})