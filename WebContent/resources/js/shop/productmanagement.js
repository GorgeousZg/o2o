$(function() {
	/*var shopId = 1;*/
	var listUrl = '/o2o/shopadmin/getproductlistbyshop?pageIndex=1&pageSize=9999';
	var deleteUrl = '/o2o/shopadmin/modifyproduct';
	getList();
	/**
	 * 获取此店铺下的商品列表
	 * @returns
	 */
	function getList() {
		//从后台获取此店铺的商品列表
		$.getJSON(listUrl, function(data) {
			if (data.success) {
				var productList = data.productList;
				var tempHtml = '';
				//遍历每条商品信息，拼接成一行显示，列信息包括;
				//商品名称,优先级，上架\下架(含productId)，编辑按钮(含productId)
				//预览(含productId)
				productList.map(function(item, index) {
					var textOp = "下架";
					var contraryStatus = 0;
					if (item.enableStatus == 0) {
						textOp = "上架";
						contraryStatus = 1;
					} else {
						contraryStatus = 0;
					}
					tempHtml += '' + '<div class="row row-product">'
							+ '<div class="col-33">'
							+ item.productName
							+ '</div>'
							+ '<div class="col-33">'
							+ item.point
							+ '</div>'
							+ '<div class="col-33">'
							+ '<a href="#" class="edit" data-id="'
							+ item.productId
							+ '" data-status="'
							+ item.enableStatus
							+ '">编辑</a>'
							+ '<a href="#" class="delete" data-id="'
							+ item.productId
							+ '" data-status="'
							+ contraryStatus
							+ '">'
							+ textOp
							+ '</a>'
							+ '<a href="#" class="preview" data-id="'
							+ item.productId
							+ '" data-status="'
							+ item.enableStatus
							+ '">预览</a>'
							+ '</div>'
							+ '</div>';
				});
				$('.product-wrap').html(tempHtml);
			}
		});
	}



	function deleteItem(id, enableStatus) {
		var product = {};
		product.productId = id;
		product.enableStatus = enableStatus;
		$.confirm('确定么?', function() {
			$.ajax({
				url : deleteUrl,
				type : 'POST',
				data : {
					productStr : JSON.stringify(product),
					statusChange : true
				},
				dataType : 'json',
				success : function(data) {
					if (data.success) {
						$.toast('操作成功！');
						getList();
					} else {
						$.toast('操作失败！');
					}
				}
			});
		});
	}

	$('.product-wrap')
			.on(
					'click',
					'a',
					function(e) {
						var target = $(e.currentTarget);
						if (target.hasClass('edit')) {
							//如果有class edit则点击就进入店铺信息编辑页面，并带有productId参数
							window.location.href = '/o2o/shopadmin/productedit?productId='
									+ e.currentTarget.dataset.id;
						} else if (target.hasClass('delete')) {
							
							deleteItem(e.currentTarget.dataset.id,
									e.currentTarget.dataset.status);
						} else if (target.hasClass('preview')) {
							window.location.href = '/o2o/frontend/productdetail?productId='
									+ e.currentTarget.dataset.id;
						}else if(target.hasClass('status')){
							//如果有class status则调用后台功能上/下架相关商品，并带有productId参数
							changgeItemStatus(e.currentTarget.dataset.id,
									e.currentTarget.dataset.status);
						}
					});
	function changgeItemStatus(id,enableStatus){
		//定义product json对象并添加productId以及状态(上架/下架)
		var product={};
		product.productId=id;
		product.enableStatus=enableStatus;
		$.confim('确定么',function(){
			//上下架相关商品
			$.ajax({
				url:statusUrl,
				type:'POST',
				data:{
					productStr:JSON.stringify(product),
					statusChange:true
				},
				dataType:'json',
				success:function(data){
					if(data.success){
						$.toast('操作成功');
						getList();
					}else{
						$.toast('操作失败');
					}
				}
			})
		})
	}
	$('#new').click(function() {
		window.location.href = '/o2o/shopadmin/productedit';
	});
});