const textarea = document.getElementById('commentTextarea');
const currentCountSpan = document.getElementById('currentCount');
const maxLength = 200;
	
currentCountSpan.textContent = textarea.value.length; 
	
textarea.addEventListener('input', function() {
	const currentLength = this.value.length;
		    
	currentCountSpan.textContent = currentLength;
	
	if (currentLength > maxLength) {
		currentCountSpan.style.color = 'red';
	} else {
		currentCountSpan.style.color = '#999';
	}
});