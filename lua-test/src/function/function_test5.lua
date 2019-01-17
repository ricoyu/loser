#!/usr/local/bin/lua
function average(...) 
	result = 0;
	local arg = {...};

	for i, v in ipairs(arg) do
		result = result + v;
	end
	print("总共传入 ".. #arg .. "个参数");
	return result/#arg;
end

print("平均值为",average(10,5,3,4,5,6));