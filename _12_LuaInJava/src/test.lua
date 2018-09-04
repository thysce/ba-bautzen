
myVar = false

function foo(a, b)
	if b and myVar then
		return math.sqrt(a) + 5
	else
		return a - 1
	end
end
