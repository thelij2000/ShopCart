addToCart_shouldAddCorrectQuantity_whenStockIsAvailable
Kiểm tra việc thêm sách vào giỏ hàng khi số lượng yêu cầu nhỏ hơn hoặc bằng số lượng còn trong kho.

addToCart_shouldAddMaxAvailableQuantity_whenRequestedExceedsStock
Kiểm tra việc thêm sách vào giỏ hàng khi số lượng yêu cầu vượt quá số lượng tồn kho.

checkout_shouldReduceStockAfterOrder
Kiểm tra chức năng đặt hàng (checkout), cụ thể là giảm số lượng sách trong kho sau khi đặt hàng.

cancelOrder_shouldRestoreStock
Kiểm tra chức năng hủy đơn hàng, cụ thể là khôi phục lại số lượng sách trong kho.

