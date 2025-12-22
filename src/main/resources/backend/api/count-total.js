const countTotalMealAndTotalAmountByDate = (params) => {
    return $axios({
        url: '/count/countTotalMealAndTotalAmountByDate',
        method: 'post',
        data: params
    })
}
